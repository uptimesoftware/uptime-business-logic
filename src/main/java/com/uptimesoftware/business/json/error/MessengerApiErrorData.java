package com.uptimesoftware.business.json.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * When the core returns an api error to the controller it also needs to tell it what http response code to use.
 */
public class MessengerApiErrorData {

	private final Object apiError;
	private final int httpResponseCode;

	@JsonCreator
	public MessengerApiErrorData(@JsonProperty("apiError") Object apiError, @JsonProperty("httpResponseCode") int httpResponseCode) {
		this.apiError = apiError;
		this.httpResponseCode = httpResponseCode;
	}

	public Object getApiError() {
		return apiError;
	}

	public int getHttpResponseCode() {
		return httpResponseCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiError, httpResponseCode);
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
		final MessengerApiErrorData other = (MessengerApiErrorData) obj;
		return Objects.equals(apiError, other.apiError) && (httpResponseCode == other.httpResponseCode);
	}

	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this).add("apiError", apiError)
				.add("httpResponseCode", httpResponseCode).toString();
	}

}
