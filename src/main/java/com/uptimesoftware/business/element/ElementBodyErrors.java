package com.uptimesoftware.business.element;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.FieldContext;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.uptimesoftware.business.validation.oval.ConstraintViolations;

public class ElementBodyErrors implements Iterable<ElementBodyError> {

	private final List<ElementBodyError> errors;

	public ElementBodyErrors(Iterable<ElementBodyError> errors) {
		this.errors = Lists.newArrayList(errors);
	}

	public ElementBodyErrors(ElementBodyError... errors) {
		this.errors = Lists.newArrayList(errors);
	}

	public ElementBodyErrors() {
		this.errors = Lists.newArrayList();
	}

	public List<ElementBodyError> getErrors() {
		return ImmutableList.copyOf(errors);
	}

	public ElementBodyErrors addError(ElementBodyError error) {
		errors.add(error);
		return this;
	}

	public ElementBodyErrors addErrors(Iterable<ElementBodyError> error) {
		Iterables.addAll(errors, error);
		return this;
	}

	public ElementBodyErrors addViolation(ConstraintViolation violation) {
		errors.add(ToAddElementValidationError.apply(violation));
		return this;
	}

	public ElementBodyErrors addViolations(Iterable<ConstraintViolation> violations) {
		Iterables.addAll(errors, Iterables.transform(violations, ToAddElementValidationError));
		return this;
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

	@Override
	@JsonIgnore
	public Iterator<ElementBodyError> iterator() {
		return errors.iterator();
	}

	@JsonIgnore
	public int size() {
		return errors.size();
	}

	@JsonIgnore
	public boolean isEmpty() {
		return errors.isEmpty();
	}

}
