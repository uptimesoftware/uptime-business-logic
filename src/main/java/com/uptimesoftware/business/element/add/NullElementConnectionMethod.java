package com.uptimesoftware.business.element.add;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * This class is so that json parsing won't fail out if we get an unknown connectionType. We wan't parsing to continue so that we
 * can return proper validation messages.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NullElementConnectionMethod extends ElementCollectionMethod {

	public NullElementConnectionMethod() {
		super(null);
	}

	// @Override
	// public ElementConnectionTypeEnum getConnectionType() {
	// return null;
	// }
	//
	// public String getConnectionTypeString() {
	// return connectionType;
	// }
	//
	// public void setConnectionType(String connectionType) {
	// this.connectionType = connectionType;
	// }
	//
}
