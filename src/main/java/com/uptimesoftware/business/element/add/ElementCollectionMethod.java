package com.uptimesoftware.business.element.add;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.uptimesoftware.business.element.ElementConnectionTypeEnum;
import com.uptimesoftware.business.element.ElementConstantStrings;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "connectionType", defaultImpl = NullElementConnectionMethod.class)
@JsonSubTypes({ @Type(value = AddAgentElementCollectionMethod.class, name = ElementConstantStrings.AGENT_CONNECTION_TYPE_JSON_VALUE),
		@Type(value = AddWmiElementCollectionMethod.class, name = ElementConstantStrings.WMI_CONNECTION_TYPE_JSON_VALUE) })
public interface ElementCollectionMethod {
	public ElementConnectionTypeEnum getConnectionType();
}
