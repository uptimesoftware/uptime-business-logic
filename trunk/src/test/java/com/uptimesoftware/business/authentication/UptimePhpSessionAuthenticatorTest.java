package com.uptimesoftware.business.authentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UptimePhpSessionAuthenticatorTest {

	private File phpSessionDirectory;

	@Mock
	private HttpServletRequest request;

	@Before
	public void createTempDir() throws IOException {
		phpSessionDirectory = Files.createTempDirectory(UptimePhpSessionAuthenticatorTest.class.getSimpleName()).toFile();
	}

	@After
	public void removeTempDir() {
		for (File file : phpSessionDirectory.listFiles()) {
			file.delete();
		}
		phpSessionDirectory.delete();
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void invalidPhpSessionDirThrowsException() throws IOException {
		File f = Files.createTempFile(UptimePhpSessionAuthenticatorTest.class.getSimpleName(), "tmp").toFile();
		try {
			new UptimePhpSessionAuthenticator(f);
		} finally {
			f.delete();
		}
	}

	@Test
	public void noCookieReturnsAbsent() throws AuthenticationException {
		UptimePhpSessionAuthenticator authenticator = new UptimePhpSessionAuthenticator(phpSessionDirectory);
		assertFalse(authenticator.getCurrentUserId(request).isPresent());
	}

	@Test
	public void noSessionFileReturnsNull() throws AuthenticationException {
		UptimePhpSessionAuthenticator authenticator = new UptimePhpSessionAuthenticator(phpSessionDirectory);
		when(request.getCookies()).thenReturn(cookie("abc"));
		assertFalse(authenticator.getCurrentUserId(request).isPresent());
	}

	@Test
	public void emptySessionFileReturnsNull() throws AuthenticationException, IOException {
		UptimePhpSessionAuthenticator authenticator = new UptimePhpSessionAuthenticator(phpSessionDirectory);
		Files.createFile(phpSessionDirectory.toPath().resolve("sess_def"));
		when(request.getCookies()).thenReturn(cookie("def"));
		assertFalse(authenticator.getCurrentUserId(request).isPresent());
	}

	@Test
	public void returnsCorrectUserId() throws AuthenticationException, IOException {
		UptimePhpSessionAuthenticator authenticator = new UptimePhpSessionAuthenticator(phpSessionDirectory);
		Files.copy(getClass().getResourceAsStream("sess_9k9r9hvjdp8j23g30tjagumm75"),
				phpSessionDirectory.toPath().resolve("sess_ghi"));
		when(request.getCookies()).thenReturn(cookie("ghi"));
		assertEquals(Long.valueOf(1), authenticator.getCurrentUserId(request).get());
	}

	private Cookie[] cookie(String value) {
		return new Cookie[] { new Cookie("PHPSESSID", value) };
	}

}
