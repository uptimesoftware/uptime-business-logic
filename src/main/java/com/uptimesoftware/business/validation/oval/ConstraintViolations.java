package com.uptimesoftware.business.validation.oval;

import javax.annotation.Nullable;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

import com.google.common.base.Function;

public class ConstraintViolations {

	public static final Function<ConstraintViolation, String> ToMessage = new Function<ConstraintViolation, String>() {
		@Override
		public String apply(@Nullable ConstraintViolation input) {
			if (input == null) {
				return null;
			}
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
