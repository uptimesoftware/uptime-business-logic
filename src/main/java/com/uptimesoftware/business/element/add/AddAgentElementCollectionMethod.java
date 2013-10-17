package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Range;

import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

public class AddAgentElementCollectionMethod implements ElementCollectionMethod {

	private final Boolean useGlobalConnectionSettings;
	@NotNull(message = "The agent port is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	@Range(min = 1, max = 65535, message = "The agent port must be a number between {min} and {max}", errorCode = AddElementErrorCodes.NUMBER_OUT_OF_RANGE)
	private final Integer port;
	@NotNull(message = "The agent use SSL value is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	private final Boolean useSSL;

	public AddAgentElementCollectionMethod(Boolean useGlobalConnectionSettings, Integer port, Boolean useSsl) {
		if (useGlobalConnectionSettings == null) {
			useGlobalConnectionSettings = false;
		}
		this.useGlobalConnectionSettings = useGlobalConnectionSettings;
		this.port = port;
		this.useSSL = useSsl;
	}

	/**
	 * for json deserialization
	 */
	public AddAgentElementCollectionMethod() {
		this(null, null, null);
	}

	@Override
	public ElementConnectionTypeEnum getConnectionType() {
		return ElementConnectionTypeEnum.Agent;
	}

	public boolean useGlobalConnectionSettings() {
		return useGlobalConnectionSettings;
	}

	public Integer getPort() {
		return port;
	}

	public boolean useSSL() {
		return useSSL;
	}

}
