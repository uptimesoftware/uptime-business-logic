package com.uptimesoftware.business.authentication;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Files;

public class UptimePhpSessionAuthenticator implements HttpServletRequestAuthenticator {

	private static final String PhpSessionCookie = "PHPSESSID";
	private static final Pattern UserIdPattern = Pattern
			.compile("current_user\\|O:10:\"UptimeUser\":[0-9]+:\\{s:[0-9]+:\"UptimeUserid\";s:[0-9]+:\"([0-9]+)\"");

	private static final int DefaultMaxLoaderRetries = 4;
	private static final int DefaultCacheTimeToLive = 10;

	private final LoadingCache<String, Optional<Long>> sessionCache;

	/**
	 * @param phpSessionDirectory
	 *            Directory under which the PHP sess_* files can be found.
	 */
	public UptimePhpSessionAuthenticator(@Nonnull File phpSessionDirectory) {
		this(phpSessionDirectory, DefaultMaxLoaderRetries, DefaultCacheTimeToLive);
	}

	/**
	 * @param phpSessionDirectory
	 *            Directory under which the PHP sess_* files can be found.
	 * @param maxLoaderRetries
	 *            Number of times to retry opening the session file if it fails with IOException (probably due to it being locked
	 *            by PHP). We wait 1-500ms between attempts.
	 */
	public UptimePhpSessionAuthenticator(@Nonnull File phpSessionDirectory, int maxLoaderRetries) {
		this(phpSessionDirectory, maxLoaderRetries, DefaultCacheTimeToLive);
	}

	/**
	 * @param phpSessionDirectory
	 *            Directory under which the PHP sess_* files can be found.
	 * @param maxLoaderRetries
	 *            Number of times to retry opening the session file if it fails with IOException (probably due to it being locked
	 *            by PHP). We wait 1-500ms between attempts.
	 * @param cacheTimeToLive
	 *            Amount of time in seconds to allow a currentUserId to remain cached.
	 */
	public UptimePhpSessionAuthenticator(@Nonnull File phpSessionDirectory, int maxLoaderRetries, int cacheTimeToLive) {
		if (!phpSessionDirectory.canRead() || !phpSessionDirectory.isDirectory()) {
			throw new IllegalArgumentException("Can't read PHP session directory: " + phpSessionDirectory.getAbsolutePath());
		}
		if (maxLoaderRetries < 0) {
			throw new IllegalArgumentException("Max retries must not be negative");
		}
		if (cacheTimeToLive < 0) {
			throw new IllegalArgumentException("Time to live must not be negative");
		}
		sessionCache = CacheBuilder.newBuilder().concurrencyLevel(1).expireAfterWrite(cacheTimeToLive, TimeUnit.SECONDS)
				.build(new UserIdLoader(phpSessionDirectory, maxLoaderRetries));
	}

	@Override
	public Optional<Long> getCurrentUserId(@Nonnull HttpServletRequest request) throws AuthenticationException {
		Optional<String> phpSessionId = getPhpSessionId(request);
		if (!phpSessionId.isPresent()) {
			return Optional.absent();
		}
		try {
			return sessionCache.get(phpSessionId.get());
		} catch (ExecutionException e) {
			throw new AuthenticationException("Error reading session file for PHP session " + phpSessionId, e.getCause());
		}
	}

	private Optional<String> getPhpSessionId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return Optional.absent();
		}
		for (Cookie cookie : cookies) {
			if (PhpSessionCookie.equals(cookie.getName())) {
				return Optional.of(cookie.getValue());
			}
		}
		return Optional.absent();
	}

	private static class UserIdLoader extends CacheLoader<String, Optional<Long>> {
		private final Random random = new Random();
		private final File phpSessionDirectory;
		private final int maxRetries;

		UserIdLoader(File phpSessionDirectory, int maxRetries) {
			this.phpSessionDirectory = phpSessionDirectory;
			this.maxRetries = maxRetries;
		}

		@Override
		public Optional<Long> load(String phpSessionId) throws IOException {
			File sessionFile = new File(phpSessionDirectory, "sess_" + phpSessionId);
			for (int attempt = 1; attempt <= maxRetries + 1; attempt++) {
				if (!sessionFile.exists()) {
					return Optional.absent();
				}
				try {
					String sessionString = Files.toString(sessionFile, Charsets.ISO_8859_1).replace("\0", "");
					Matcher m = UserIdPattern.matcher(sessionString);
					if (m.find()) {
						String userIdString = m.group(1);
						return Optional.of(Long.parseLong(userIdString));
					}
				} catch (IOException e) {
					if (attempt == maxRetries + 1) {
						throw e;
					}
					try {
						Thread.sleep((long) (random.nextDouble() * 500) + 1);
					} catch (InterruptedException e1) {
						Thread.currentThread().interrupt();
						throw e;
					}
				}
			}
			return Optional.absent();
		}
	}

}
