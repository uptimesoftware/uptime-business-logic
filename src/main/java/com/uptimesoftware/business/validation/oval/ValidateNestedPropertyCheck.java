package com.uptimesoftware.business.validation.oval;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

/**
 * Oval validation class for ValidateNestedProperty. See
 * http://bijubnair.blogspot.com/2011/03/validation-of-nested-properties-with.html
 */
public class ValidateNestedPropertyCheck extends AbstractAnnotationCheck<ValidateNestedProperty> {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator)
			throws OValException {
		if (valueToValidate != null) {

			if (valueToValidate instanceof Collection<?>) {
				// valueToValidate is a collection
				Collection<?> col = (Collection<?>) valueToValidate;

				for (Object object : col) {
					List<ConstraintViolation> violations = validator.validate(object);
					addViolations(validator, violations);
				}
			}
			if (valueToValidate.getClass().isArray()) {
				// valueToValidate is an array
				int length = Array.getLength(valueToValidate);
				for (int i = 0; i < length; i++) {
					Object o = Array.get(valueToValidate, i);
					List<ConstraintViolation> violations = validator.validate(o);
					addViolations(validator, violations);
				}
			} else {
				// valueToValidate is other object
				List<ConstraintViolation> violations = validator.validate(valueToValidate);
				addViolations(validator, violations);
			}
		}
		return true; // value is always true, since we do validation only to nested object.
	}

	private void addViolations(Validator validator, List<ConstraintViolation> violations) {
		for (ConstraintViolation constraintViolation : violations) {
			validator.reportConstraintViolation(constraintViolation);
		}
	}

}