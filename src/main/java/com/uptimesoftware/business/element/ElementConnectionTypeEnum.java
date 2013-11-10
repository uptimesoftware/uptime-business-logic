package com.uptimesoftware.business.element;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum ElementConnectionTypeEnum {
	Agent(ElementConstantStrings.AGENT_CONNECTION_TYPE_JSON_VALUE, ElementConstantStrings.ADD_AGENT_RPC_SERVICE),
	Wmi(ElementConstantStrings.WMI_CONNECTION_TYPE_JSON_VALUE, ElementConstantStrings.ADD_WMI_RPC_SERVICE);

	private static final Map<String, ElementConnectionTypeEnum> JSON_NAMES_MAP;

	static {
		Builder<String, ElementConnectionTypeEnum> builder = ImmutableMap.builder();
		for (ElementConnectionTypeEnum value : ElementConnectionTypeEnum.values()) {
			builder.put(value.getJsonName(), value);
		}
		JSON_NAMES_MAP = builder.build();
	}

	private String jsonName;
	private String rpcServiceName;

	private ElementConnectionTypeEnum(String jsonName, String rpcServiceName) {
		this.jsonName = jsonName;
		this.rpcServiceName = rpcServiceName;
	}

	public String getJsonName() {
		return jsonName;
	}

	public String getRpcServiceName() {
		return rpcServiceName;
	}

	public static ElementConnectionTypeEnum fromJsonName(String jsonName) {
		return JSON_NAMES_MAP.get(jsonName);
	}

}
