package com.uptimesoftware.business.element;

import java.util.List;

import javax.annotation.Nullable;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.uptimesoftware.business.validation.oval.ConstraintViolations;

public class ElementBodyErrors {

	private final List<ElementBodyError> errors;

	public ElementBodyErrors(List<ElementBodyError> errors) {
		this.errors = errors;
	}

	public List<ElementBodyError> getErrors() {
		return errors;
	}

	public static ElementBodyErrors create(List<ConstraintViolation> violations) {
		return new ElementBodyErrors(ImmutableList.<ElementBodyError> builder()
				.addAll(Iterables.transform(violations, ToAddElementValidationError)).build());
	}

	public static final Function<ConstraintViolation, ElementBodyError> ToAddElementValidationError = new Function<ConstraintViolation, ElementBodyError>() {
		@Override
		public ElementBodyError apply(@Nullable ConstraintViolation input) {
			if (input == null) {
				return null;
			}
			FieldContext fieldContext = ConstraintViolations.getFieldContext(input.getContext());
			String field = "unknown";
			if (fieldContext != null) {
				field = fieldContext.getField().getName();
			}
			String invalidValue = input.getInvalidValue() == null ? null : input.getInvalidValue().toString();
			return new ElementBodyError(field, input.getErrorCode(), input.getMessage(), invalidValue);
		}
	};

}
