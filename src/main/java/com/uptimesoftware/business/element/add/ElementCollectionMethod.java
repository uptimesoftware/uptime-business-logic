package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.NotNull;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;
import com.uptimesoftware.business.element.ElementConstantStrings;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "connectionType", defaultImpl = NullElementConnectionMethod.class)
@JsonSubTypes({
		@Type(value = AddAgentElementCollectionMethod.class, name = ElementConstantStrings.AGENT_CONNECTION_TYPE_JSON_VALUE),
		@Type(value = AddWmiElementCollectionMethod.class, name = ElementConstantStrings.WMI_CONNECTION_TYPE_JSON_VALUE) })
public class ElementCollectionMethod {

	@NotNull(message = "A valid connectionType value is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD)
	private final ElementConnectionTypeEnum connectionType;

	public ElementCollectionMethod(ElementConnectionTypeEnum connectionType) {
		this.connectionType = connectionType;
	}

	public ElementConnectionTypeEnum getConnectionType() {
		return connectionType;
	}

}
