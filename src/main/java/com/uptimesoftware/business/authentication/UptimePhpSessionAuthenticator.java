package com.uptimesoftware.business.authentication;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class UptimePhpSessionAuthenticator implements HttpServletRequestAuthenticator {

	private static final Pattern UserIdPattern = Pattern
			.compile("current_user\\|O:10:\"UptimeUser\":[0-9]+:\\{s:[0-9]+:\"UptimeUserid\";s:[0-9]+:\"([0-9]+)\"");

	private final File phpSessionDirectory;

	public UptimePhpSessionAuthenticator(@Nonnull File phpSessionDirectory) {
		if (!phpSessionDirectory.canRead() || !phpSessionDirectory.isDirectory()) {
			throw new IllegalArgumentException("Can't read PHP session directory: " + phpSessionDirectory.getAbsolutePath());
		}
		this.phpSessionDirectory = phpSessionDirectory;
	}

	@Override
	@Nullable
	public Long getCurrentUserId(@Nonnull HttpServletRequest request) throws AuthenticationException {
		String phpSessionId = getPhpSessionId(request);
		if (phpSessionId == null) {
			return null;
		}
		File sessionFile = new File(phpSessionDirectory, "sess_" + phpSessionId);
		if (!sessionFile.exists()) {
			return null;
		}
		try {
			String sessionString = Files.toString(sessionFile, Charsets.ISO_8859_1).replace("\0", "");
			Matcher m = UserIdPattern.matcher(sessionString);
			if (m.find()) {
				String userIdString = m.group(1);
				return Long.parseLong(userIdString);
			}
		} catch (IOException e) {
			throw new AuthenticationException("Error reading session file " + sessionFile.getAbsolutePath(), e);
		}
		return null;
	}

	private String getPhpSessionId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if ("PHPSESSID".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
