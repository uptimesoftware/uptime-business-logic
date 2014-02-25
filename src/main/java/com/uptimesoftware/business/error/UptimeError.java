package com.uptimesoftware.business.error;

import java.util.Objects;

import com.google.common.base.Equivalence;

public interface UptimeError {

	public static final Equivalence<UptimeError> EQUIVALENCE = new Equivalence<UptimeError>() {

		@Override
		protected int doHash(UptimeError t) {
			return Objects.hash(t.getCode(), t.getHttpStatus(), t.getDescription());
		}

		@Override
		protected boolean doEquivalent(UptimeError a, UptimeError b) {
			return Objects.equals(a.getCode(), b.getCode()) && Objects.equals(a.getHttpStatus(), b.getHttpStatus())
					&& Objects.equals(a.getDescription(), b.getDescription());
		}
	};

	public String getCode();

	public int getHttpStatus();

	public String getDescription();
}
