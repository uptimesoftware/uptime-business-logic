package com.uptimesoftware.business.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
	private final String encrypted;

	private Password(String encrypted) {
		this.encrypted = encrypted;
	}

	/**
	 * Get a Password object for the given encrypted password.
	 */
	public static Password forEncrypted(String encrypted) {
		return new Password(encrypted);
	}

	/**
	 * Get a Password object for the given plaintext password.
	 */
	public static Password forPlaintext(String plaintext) {
		return new Password(encrypt(plaintext));
	}

	/**
	 * Check whether the password is valid.
	 */
	public boolean isValidPassword(String plaintext) {
		return encrypt(plaintext).equals(encrypted);
	}

	/**
	 * Get the encrypted version of the password.
	 */
	public String getEncrypted() {
		return encrypted;
	}

	private static String encrypt(String plaintext) {
		MessageDigest md;
		try {
			// (pete) - should add random salt and store it with the
			// encrypted password
			md = MessageDigest.getInstance("SHA");
			md.update(plaintext.getBytes());

			byte[] bytes = md.digest();
			return hexstring(bytes);
		} catch (NoSuchAlgorithmException e) {
			// Can't handle this, can't ignore it, can't force
			// callers to deal with it.
			throw new RuntimeException(e);
		}
	}

	/**
	 * Convert bytes to hex digits
	 * 
	 * @param data
	 *            Bytes to convert
	 * @return String of hex digits
	 */
	private static String hexstring(byte[] data) {
		char[] ch;
		int i;

		ch = new char[data.length * 2];
		i = 0;

		for (int val : data) {
			int b = val & 0xFF;
			int topNybble = b >> 4;
			ch[i++] = hexDigitFor(topNybble);

			int bottomNybble = b & 0xF;
			ch[i++] = hexDigitFor(bottomNybble);
		}

		return new String(ch);
	}

	private static final String HEX = "0123456789abcdef";

	private static char hexDigitFor(int nybble) {
		return HEX.charAt(nybble);
	}
}
