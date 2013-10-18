package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.NotNull;

import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

public interface ElementCollectionMethod {
	@NotNull(message = "The connection type value is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	public ElementConnectionTypeEnum getConnectionType();
}
