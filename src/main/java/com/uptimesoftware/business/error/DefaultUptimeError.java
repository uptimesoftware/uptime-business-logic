package com.uptimesoftware.business.error;


public class DefaultUptimeError implements UptimeError {

	private final String code;
	private final int httpStatus;
	private final String description;

	public DefaultUptimeError(String code, int httpStatus, String description) {
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

	@Override
	public int hashCode() {
		return UptimeError.EQUIVALENCE.hash(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof UptimeError)) {
			return false;
		}
		UptimeError other = (UptimeError) obj;
		return UptimeError.EQUIVALENCE.equivalent(this, other);
	}

	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this).add("code", code).add("httpStatus", httpStatus)
				.add("description", description).toString();
	}
}
