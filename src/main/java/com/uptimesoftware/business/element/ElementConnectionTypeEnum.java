package com.uptimesoftware.business.element;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import java.util.Map;

public enum ElementConnectionTypeEnum {
	Agent(
			ElementConstantStrings.AGENT_CONNECTION_TYPE_JSON_VALUE,
			ElementConstantStrings.ADD_AGENT_RPC_SERVICE,
			EntityTypeEnum.System,
			EntitySubTypeEnum.Agent),
	Wmi(
			ElementConstantStrings.WMI_CONNECTION_TYPE_JSON_VALUE,
			ElementConstantStrings.ADD_WMI_RPC_SERVICE,
			EntityTypeEnum.System,
			EntitySubTypeEnum.WmiAgentless),
	NetworkDevice(
			ElementConstantStrings.NETWORK_DEVICE_CONNECTION_TYPE_JSON_VALUE,
			ElementConstantStrings.ADD_NETWORK_DEVICE_RPC_SERVICE,
			EntityTypeEnum.Node,
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
	private EntityTypeEnum entityType;
	private EntitySubTypeEnum entitySubType;

	private ElementConnectionTypeEnum(String jsonName, String rpcServiceName, EntityTypeEnum entityType,
			EntitySubTypeEnum entitySubType) {
		this.jsonName = jsonName;
		this.rpcServiceName = rpcServiceName;
		this.entityType = entityType;
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

	public EntityTypeEnum getEntityType() {
		return entityType;
	}

	public EntitySubTypeEnum getEntitySubType() {
		return entitySubType;
	}

}
