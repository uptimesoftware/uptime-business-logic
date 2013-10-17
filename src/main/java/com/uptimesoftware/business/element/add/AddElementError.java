package com.uptimesoftware.business.element.add;

public class AddElementError {
	private final String field;
	private final String errorCode;
	private final String errorMessage;
	private final String value;

	public AddElementError(String field, String errorCode, String errorMessage, String value) {
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
