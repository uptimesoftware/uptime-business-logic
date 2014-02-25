package com.uptimesoftware.business.element.add;

import java.util.Objects;

import net.sf.oval.constraint.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.google.common.base.Objects.ToStringHelper;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;
import com.uptimesoftware.business.element.ElementConstantStrings;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "connectionType", defaultImpl = NullElementConnectionMethod.class)
@JsonSubTypes({
		@Type(value = AddAgentElementCollectionMethod.class, name = ElementConstantStrings.AGENT_CONNECTION_TYPE_JSON_VALUE),
		@Type(value = AddWmiElementCollectionMethod.class, name = ElementConstantStrings.WMI_CONNECTION_TYPE_JSON_VALUE),
		@Type(value = AddNetworkDeviceElementCollectionMethod.class, name = ElementConstantStrings.NETWORK_DEVICE_CONNECTION_TYPE_JSON_VALUE) })
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
