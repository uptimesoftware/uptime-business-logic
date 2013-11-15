package com.uptimesoftware.business.validation.oval;

import net.sf.oval.constraint.CheckWithCheck;

public class ContainsNoSpacesCheck implements CheckWithCheck.SimpleCheck {
	String regex;

	@Override
	public boolean isSatisfied(Object validatedObject, Object value) {
		return !((String) value).contains(" ");
	}

}
