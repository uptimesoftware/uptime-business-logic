package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

public class AddWmiElementCollectionMethod extends ElementCollectionMethod {

	private final Boolean useGlobalConnectionSettings;
	@NotNull(message = "The WMI Domain is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	@Length(max = 255, message = "The WMI Domain must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	private final String wmiDomain;
	@NotNull(message = "The WMI Username is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	@Length(max = 255, message = "The WMI Username must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	private final String wmiUsername;
	@NotNull(message = "The WMI Password is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	@Length(max = 255, message = "The WMI Password must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	private final String wmiPassword;

	public AddWmiElementCollectionMethod(Boolean useGlobalConnectionSettings, String wmiDomain, String wmiUsername,
			String wmiPassword) {
		super(ElementConnectionTypeEnum.Wmi);
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
