package com.uptimesoftware.business.element.add;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.Objects.ToStringHelper;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;
import com.uptimesoftware.business.element.ElementConstantStrings;
import net.sf.oval.constraint.NotNull;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "connectionType", defaultImpl = NullElementConnectionMethod.class)
@JsonSubTypes({
		@JsonSubTypes.Type(value = AddAgentElementCollectionMethod.class, name = ElementConstantStrings.AGENT_CONNECTION_TYPE_JSON_VALUE),
		@JsonSubTypes.Type(value = AddWmiElementCollectionMethod.class, name = ElementConstantStrings.WMI_CONNECTION_TYPE_JSON_VALUE),
		@JsonSubTypes.Type(value = AddNetworkDeviceElementCollectionMethod.class, name = ElementConstantStrings.NETWORK_DEVICE_CONNECTION_TYPE_JSON_VALUE),
		@JsonSubTypes.Type(value = AddHypervElementCollectionMethod.class, name = ElementConstantStrings.HYPERV_CONNECTION_TYPE_JSON_VALUE) })
public abstract class ElementCollectionMethod {

	@NotNull(message = "A valid connectionType value is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	private final ElementConnectionTypeEnum connectionType;

	protected ElementCollectionMethod(ElementConnectionTypeEnum connectionType) {
		this.connectionType = connectionType;
	}

	@JsonIgnore
	public ElementConnectionTypeEnum getConnectionType() {
		return connectionType;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(connectionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ElementCollectionMethod other = (ElementCollectionMethod) obj;
		if (connectionType != other.connectionType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return toStringHelper().add("connectionType", connectionType).toString();
	}

	protected ToStringHelper toStringHelper() {
		return com.google.common.base.Objects.toStringHelper(this).add("connectionType", connectionType);
	}
}
