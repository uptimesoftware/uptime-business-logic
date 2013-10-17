package com.uptimesoftware.business.element.add;

import java.util.List;

import javax.annotation.Nullable;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.uptimesoftware.business.validation.oval.ConstraintViolations;

public class AddElementErrors {

	private final List<AddElementError> errors;

	public AddElementErrors(List<AddElementError> errors) {
		this.errors = errors;
	}

	public List<AddElementError> getErrors() {
		return errors;
	}

	public static AddElementErrors create(List<ConstraintViolation> violations) {
		return new AddElementErrors(ImmutableList.<AddElementError> builder()
				.addAll(Iterables.transform(violations, ToAddElementValidationError)).build());
	}

	public static final Function<ConstraintViolation, AddElementError> ToAddElementValidationError = new Function<ConstraintViolation, AddElementError>() {
		@Override
		public AddElementError apply(@Nullable ConstraintViolation input) {
			if (input == null) {
				return null;
			}
			FieldContext fieldContext = ConstraintViolations.getFieldContext(input.getContext());
			String field = "unknown";
			if (fieldContext != null) {
				field = fieldContext.getField().getName();
			}
			String invalidValue = input.getInvalidValue() == null ? null : input.getInvalidValue().toString();
			return new AddElementError(field, input.getErrorCode(), input.getMessage(), invalidValue);
		}
	};

}
