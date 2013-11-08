package com.uptimesoftware.business.element.add;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NullElementConnectionMethod implements ElementCollectionMethod {

	@Override
	public ElementConnectionTypeEnum getConnectionType() {
		return null;
	}

}
