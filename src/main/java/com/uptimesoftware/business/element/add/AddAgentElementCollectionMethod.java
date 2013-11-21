package com.uptimesoftware.business.element.add;

import java.util.Objects;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Range;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Objects.ToStringHelper;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;

public class AddAgentElementCollectionMethod extends ElementCollectionMethod {

	private final Boolean useGlobalConnectionSettings;
	@NotNull(message = "The agent port is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	@Range(min = 1, max = 65535, message = "The agent port must be a number between {min} and {max}", errorCode = ElementBodyErrorCodes.NUMBER_OUT_OF_RANGE, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	private final Integer port;
	@NotNull(message = "The agent use SSL value is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()")
	private final Boolean useSSL;

	@JsonCreator
	public AddAgentElementCollectionMethod(@JsonProperty("useGlobalConnectionSettings") Boolean useGlobalConnectionSettings,
			@JsonProperty("port") Integer port, @JsonProperty("useSSL") Boolean useSsl) {
		super(ElementConnectionTypeEnum.Agent);
		if (useGlobalConnectionSettings == null) {
			useGlobalConnectionSettings = false;
		}
		this.useGlobalConnectionSettings = useGlobalConnectionSettings;
		this.port = port;
		this.useSSL = useSsl;
	}

	public Boolean isUseGlobalConnectionSettings() {
		return useGlobalConnectionSettings;
	}

	public Integer getPort() {
		return port;
	}

	public Boolean isUseSSL() {
		return useSSL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), useGlobalConnectionSettings, port, useSSL);
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
		AddAgentElementCollectionMethod other = (AddAgentElementCollectionMethod) obj;
		return Objects.equals(useGlobalConnectionSettings, other.useGlobalConnectionSettings) && Objects.equals(port, other.port)
				&& Objects.equals(useSSL, other.useSSL);
	}

	@Override
	protected ToStringHelper toStringHelper() {
		return super.toStringHelper().add("useGlobalConnectionSettings", useGlobalConnectionSettings).add("port", port)
				.add("useSSL", useSSL);
	}
}
