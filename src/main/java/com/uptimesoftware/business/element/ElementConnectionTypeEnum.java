package com.uptimesoftware.business.element;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum ElementConnectionTypeEnum {
	Agent(
			ElementConstantStrings.AGENT_CONNECTION_TYPE_JSON_VALUE,
			ElementConstantStrings.ADD_AGENT_RPC_SERVICE,
			EntitySubTypeEnum.Agent),
	Wmi(
			ElementConstantStrings.WMI_CONNECTION_TYPE_JSON_VALUE,
			ElementConstantStrings.ADD_WMI_RPC_SERVICE,
			EntitySubTypeEnum.WmiAgentless),
	NetworkDevice(
			ElementConstantStrings.NETWORK_DEVICE_CONNECTION_TYPE_JSON_VALUE,
			ElementConstantStrings.ADD_NETWORK_DEVICE_RPC_SERVICE,
			EntitySubTypeEnum.NetworkDevice);

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
	private EntitySubTypeEnum entitySubType;

	private ElementConnectionTypeEnum(String jsonName, String rpcServiceName, EntitySubTypeEnum entitySubType) {
		this.jsonName = jsonName;
		this.rpcServiceName = rpcServiceName;
		this.entitySubType = entitySubType;
	}

	@JsonValue
	public String getJsonName() {
		return jsonName;
	}

	public String getRpcServiceName() {
		return rpcServiceName;
	}

	@JsonCreator
	public static ElementConnectionTypeEnum fromJsonName(String jsonName) {
		return JSON_NAMES_MAP.get(jsonName);
	}

	public EntitySubTypeEnum getEntitySubType() {
		return entitySubType;
	}

}
