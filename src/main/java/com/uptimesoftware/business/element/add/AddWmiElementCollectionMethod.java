package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

public class AddWmiElementCollectionMethod implements ElementCollectionMethod {

	private final Boolean useGlobalConnectionSettings;
	@NotNull(message = "The WMI Domain is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	@Length(max = 255, message = "The WMI Domain must be not be more than {max} characters in length", errorCode = AddElementErrorCodes.TOO_LONG)
	private final String wmiDomain;
	@NotNull(message = "The WMI Username is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	@Length(max = 255, message = "The WMI Username must be not be more than {max} characters in length", errorCode = AddElementErrorCodes.TOO_LONG)
	private final String wmiUsername;
	@NotNull(message = "The WMI Password is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	@Length(max = 255, message = "The WMI Password must be not be more than {max} characters in length", errorCode = AddElementErrorCodes.TOO_LONG)
	private final String wmiPassword;

	public AddWmiElementCollectionMethod(Boolean useGlobalConnectionSettings, String wmiDomain, String wmiUsername,
			String wmiPassword) {
		if (useGlobalConnectionSettings == null) {
			useGlobalConnectionSettings = false;
		}
		this.useGlobalConnectionSettings = useGlobalConnectionSettings;
		this.wmiDomain = wmiDomain;
		this.wmiUsername = wmiUsername;
		this.wmiPassword = wmiPassword;
	}

	/**
	 * for json deserialization
	 */
	public AddWmiElementCollectionMethod() {
		this(null, null, null, null);
	}

	@Override
	public ElementConnectionTypeEnum getConnectionType() {
		return ElementConnectionTypeEnum.Agent;
	}

	public Boolean isUseGlobalConnectionSettings() {
		return useGlobalConnectionSettings;
	}

	public String getWmiDomain() {
		return wmiDomain;
	}

	public String getWmiUsername() {
		return wmiUsername;
	}

	public String getWmiPassword() {
		return wmiPassword;
	}

}
