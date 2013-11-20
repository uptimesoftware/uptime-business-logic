package com.uptimesoftware.business.json.error;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ApiError {
	private final String error;
	private final String errorDescription;

	@JsonCreator
	public ApiError(@JsonProperty("error") String error, @JsonProperty("errorDescription") String errorDescription) {
		this.error = error;
		this.errorDescription = errorDescription;
	}

	public String getError() {
		return error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(error, errorDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ApiError other = (ApiError) obj;
		return Objects.equals(error, other.error) && Objects.equals(errorDescription, other.errorDescription);
	}

	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this).add("error", error).add("errorDescription", errorDescription)
				.toString();
	}

}
