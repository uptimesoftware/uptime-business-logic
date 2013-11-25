package com.uptimesoftware.business.error;

public interface UptimeError {

	public String getCode();

	public int getHttpStatus();

	public String getDescription();
}
