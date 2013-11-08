package com.uptimesoftware.business.element;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum ElementConnectionTypeEnum {
	Agent(ElementConnectionTypeNames.AGENT_VALUE),
	Wmi(ElementConnectionTypeNames.WMI_VALUE);

	private static final Map<String, ElementConnectionTypeEnum> JSON_NAMES_MAP;

	static {
		Builder<String, ElementConnectionTypeEnum> builder = ImmutableMap.builder();
		for (ElementConnectionTypeEnum value : ElementConnectionTypeEnum.values()) {
			builder.put(value.getJsonName(), value);
		}
		JSON_NAMES_MAP = builder.build();
	}

	private String jsonName;

	private ElementConnectionTypeEnum(String jsonName) {
		this.jsonName = jsonName;
	}

	public String getJsonName() {
		return jsonName;
	}

	public static ElementConnectionTypeEnum fromJsonName(String jsonName) {
		return JSON_NAMES_MAP.get(jsonName);
	}

}
