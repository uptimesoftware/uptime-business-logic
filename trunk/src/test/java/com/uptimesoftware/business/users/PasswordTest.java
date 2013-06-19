package com.uptimesoftware.business.users;

import junit.framework.TestCase;

import com.uptimesoftware.business.users.Password;

public class PasswordTest extends TestCase {
	private Password password;
	private String plaintext;

	@Override
	public void setUp() {
		plaintext = "s3cr3t";
		password = Password.forPlaintext(plaintext);
	}

	public void testIsValid() {
		assertTrue(password.isValidPassword(plaintext));
		assertFalse(password.isValidPassword("wrong"));
	}

	public void testEncryptedValue() {
		// 40 hex chars = 160 bits, SHA-1 standard
		String encrypted = password.getEncrypted();
		assertEquals(40, encrypted.length());
	}

	public void testCompatibleWithPhp() {
		// Passwords are set in the PHP currently, so we have to be
		// compatible. It's OK to remove this test once we move it
		// to Java.
		String encrypted = password.getEncrypted();
		assertEquals("25ab86bed149ca6ca9c1c0d5db7c9a91388ddeab", encrypted);
	}

	public void testCompatibleWithInitialDatabase() {
		// Uptime user's password is set in uptime_v4_ddl.sql, so
		// we have to be compatible.
		password = Password.forPlaintext("admin");
		assertEquals("d033e22ae348aeb5660fc2140aec35850c4da997", password.getEncrypted());
	}
}
