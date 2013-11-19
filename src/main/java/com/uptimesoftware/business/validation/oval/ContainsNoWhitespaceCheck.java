package com.uptimesoftware.business.validation.oval;

import net.sf.oval.constraint.CheckWithCheck;

public class ContainsNoWhitespaceCheck implements CheckWithCheck.SimpleCheck {
	@Override
	public boolean isSatisfied(Object validatedObject, Object value) {
		if (value == null) {
			return true;
		}

		final String str = value.toString();

		final int l = str.length();
		for (int i = 0; i < l; i++) {
			final char ch = str.charAt(i);
			if (Character.isSpaceChar(ch) || Character.isWhitespace(ch)) {
				return false;
			}
		}

		return true;
	}

}
