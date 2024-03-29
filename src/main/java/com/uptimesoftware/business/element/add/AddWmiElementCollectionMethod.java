package com.uptimesoftware.business.element.add;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects.ToStringHelper;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import java.util.Objects;

public class AddWmiElementCollectionMethod extends ElementCollectionMethod {

	private static final String NOT_GLOBAL = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()";
	private final Boolean useGlobalConnectionSettings;
	@Length(max = 255, message = "The WMI Domain must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045, when = NOT_GLOBAL)
	private final String wmiDomain;
	@NotNull(message = "The WMI Username is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043, when = NOT_GLOBAL)
	@Length(max = 255, message = "The WMI Username must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045, when = NOT_GLOBAL)
	private final String wmiUsername;
	@NotNull(message = "The WMI Password is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043, when = NOT_GLOBAL)
	@Length(max = 255, message = "The WMI Password must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045, when = NOT_GLOBAL)
	private final String wmiPassword;

	@JsonCreator
	public AddWmiElementCollectionMethod(@JsonProperty("useGlobalConnectionSettings") Boolean useGlobalConnectionSettings,
			@JsonProperty("wmiDomain") String wmiDomain, @JsonProperty("wmiUsername") String wmiUsername,
			@JsonProperty("wmiPassword") String wmiPassword) {
		super(ElementConnectionTypeEnum.Wmi);
		if (useGlobalConnectionSettings == null) {
			useGlobalConnectionSettings = false;
		}
		this.useGlobalConnectionSettings = useGlobalConnectionSettings;
		this.wmiDomain = wmiDomain;
		this.wmiUsername = wmiUsername;
		this.wmiPassword = wmiPassword;
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

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), useGlobalConnectionSettings, wmiDomain, wmiUsername, wmiPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AddWmiElementCollectionMethod other = (AddWmiElementCollectionMethod) obj;
		return Objects.equals(useGlobalConnectionSettings, other.useGlobalConnectionSettings)
				&& Objects.equals(wmiDomain, other.wmiDomain) && Objects.equals(wmiUsername, other.wmiUsername)
				&& Objects.equals(wmiPassword, other.wmiPassword);
	}

	@Override
	protected ToStringHelper toStringHelper() {
		return super.toStringHelper().add("useGlobalConnectionSettings", useGlobalConnectionSettings).add("wmiDomain", wmiDomain)
				.add("wmiUsername", wmiUsername).add("wmiPassword", wmiPassword);
	}
}
