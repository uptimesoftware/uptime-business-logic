package com.uptimesoftware.business.validation.oval;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

import com.google.common.base.Function;

public class ConstraintViolations {

	public static final Function<ConstraintViolation, String> ToMessage = new Function<ConstraintViolation, String>() {
		@Override
		public String apply(ConstraintViolation input) {
			return input.getMessage();
		}
	};

	public static FieldContext getFieldContext(OValContext context) {
		if (FieldContext.class != context.getClass()) {
			return null;
		}
		return (FieldContext) context;
	}

}
