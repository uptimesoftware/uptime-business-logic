package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.NotNull;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.uptimesoftware.business.element.ElementConnectionTypeEnum;
import com.uptimesoftware.business.element.ElementConnectionTypeNames;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "connectionType", defaultImpl = NullElementConnectionMethod.class)
@JsonSubTypes({ @Type(value = AddAgentElementCollectionMethod.class, name = ElementConnectionTypeNames.AGENT_VALUE) })
public interface ElementCollectionMethod {
	@NotNull(message = "The connection type value is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	public ElementConnectionTypeEnum getConnectionType();
}
