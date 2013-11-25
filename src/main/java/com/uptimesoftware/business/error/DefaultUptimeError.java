package com.uptimesoftware.business.error;

public class DefaultUptimeError implements UptimeError {

	private final String code;
	private final int httpStatus;
	private final String description;

	private DefaultUptimeError(String code, int httpStatus, String description) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.description = description;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public int getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
