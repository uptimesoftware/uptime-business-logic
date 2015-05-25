package com.uptimesoftware.business.element;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import java.util.Map;

public enum ElementTypeEnum {
	Server("Server"),
	NetworkDevice("Network Device"),
	Application("Application"),
	JavaApp("JavaApp"),
	JavaAppInstance("JavaAppInstance");

	private static final Map<String, ElementTypeEnum> JSON_NAMES_MAP;

	static {
		Builder<String, ElementTypeEnum> builder = ImmutableMap.builder();
		for (ElementTypeEnum value : ElementTypeEnum.values()) {
			builder.put(value.getDefaultName(), value);
		}
		JSON_NAMES_MAP = builder.build();
	}

	private final String defaultName;

	private ElementTypeEnum(String defaultName) {
		this.defaultName = defaultName;
	}

	@JsonValue
	public String getDefaultName() {
		return defaultName;
	}

	@JsonCreator
	public static ElementTypeEnum fromJsonName(String jsonName) {
		return JSON_NAMES_MAP.get(jsonName);
	}
}
