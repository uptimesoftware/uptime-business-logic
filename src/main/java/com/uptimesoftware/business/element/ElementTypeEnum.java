package com.uptimesoftware.business.element;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum ElementTypeEnum {
	Server("Server"),
	NetworkDevice("Network Device"),
	Application("Application");

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
