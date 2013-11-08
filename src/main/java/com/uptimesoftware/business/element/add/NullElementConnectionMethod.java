package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

/**
 * This class is so that json parsing won't fail out if we get an unknown connectionType. We wan't parsing to continue so that we
 * can return proper validation messages.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NullElementConnectionMethod implements ElementCollectionMethod {

	@NotNull(message = "A valid connectionType value is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	private String connectionType;

	@Override
	public ElementConnectionTypeEnum getConnectionType() {
		return null;
	}

	public String getConnectionTypeString() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

}
