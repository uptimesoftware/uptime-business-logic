package com.uptimesoftware.business.element;

public enum ElementTypeEnum {
	Server("Server"),
	NetworkDevice("Network Device"),
	Application("Application");

	private final String defaultName;

	private ElementTypeEnum(String defaultName) {
		this.defaultName = defaultName;
	}

	public String getDefaultName() {
		return defaultName;
	}
}
