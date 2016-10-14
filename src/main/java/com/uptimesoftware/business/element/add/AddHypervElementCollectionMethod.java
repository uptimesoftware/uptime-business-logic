package com.uptimesoftware.business.element.add;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects.ToStringHelper;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import java.util.Objects;

public class AddHypervElementCollectionMethod extends ElementCollectionMethod {

	private static final String NOT_GLOBAL = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()";
	private final Boolean useGlobalConnectionSettings;
	@Length(max = 255, message = "The Hyper-V Domain must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045, when = NOT_GLOBAL)
	private final String hypervDomain;
	@NotNull(message = "The Hyper-V Username is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043, when = NOT_GLOBAL)
	@Length(max = 255, message = "The Hyper-V Username must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045, when = NOT_GLOBAL)
	private final String hypervUsername;
	@NotNull(message = "The Hyper-V Password is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043, when = NOT_GLOBAL)
	@Length(max = 255, message = "The Hyper-V Password must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045, when = NOT_GLOBAL)
	private final String hypervPassword;

	@JsonCreator
	public AddHypervElementCollectionMethod(@JsonProperty("useGlobalConnectionSettings") Boolean useGlobalConnectionSettings,
			@JsonProperty("hypervDomain") String hypervDomain, @JsonProperty("hypervUsername") String hypervUsername,
			@JsonProperty("hypervPassword") String hypervPassword) {
		super(ElementConnectionTypeEnum.Hyperv);
		if (useGlobalConnectionSettings == null) {
			useGlobalConnectionSettings = false;
		}
		this.useGlobalConnectionSettings = useGlobalConnectionSettings;
		this.hypervDomain = hypervDomain;
		this.hypervUsername = hypervUsername;
		this.hypervPassword = hypervPassword;
	}

	public Boolean isUseGlobalConnectionSettings() {
		return useGlobalConnectionSettings;
	}

	public String getHypervDomain() {
		return hypervDomain;
	}

	public String getHypervUsername() {
		return hypervUsername;
	}

	public String getHypervPassword() {
		return hypervPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), useGlobalConnectionSettings, hypervDomain, hypervUsername, hypervPassword);
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
		AddHypervElementCollectionMethod other = (AddHypervElementCollectionMethod) obj;
		return Objects.equals(useGlobalConnectionSettings, other.useGlobalConnectionSettings)
				&& Objects.equals(hypervDomain, other.hypervDomain) && Objects.equals(hypervUsername, other.hypervUsername)
				&& Objects.equals(hypervPassword, other.hypervPassword);
	}

	@Override
	protected ToStringHelper toStringHelper() {
		return super.toStringHelper().add("useGlobalConnectionSettings", useGlobalConnectionSettings).add("hypervDomain", hypervDomain)
				.add("hypervUsername", hypervUsername).add("hypervPassword", hypervPassword);
	}
}
