package com.uptimesoftware.business.element;

public class ElementBodyError {
	private final String field;
	private final String errorCode;
	private final String errorMessage;
	private final String value;

	public ElementBodyError(String field, String errorCode, String errorMessage, String value) {
		this.field = field;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getValue() {
		return value;
	}

}
