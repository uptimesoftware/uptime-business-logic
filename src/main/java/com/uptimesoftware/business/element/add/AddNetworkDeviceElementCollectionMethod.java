package com.uptimesoftware.business.element.add;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Range;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Enums;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementConnectionTypeEnum;
import com.uptimesoftware.business.snmp.SnmpV3AuthenticationMethod;
import com.uptimesoftware.business.snmp.SnmpV3PrivacyType;
import com.uptimesoftware.business.snmp.SnmpVersion;

public class AddNetworkDeviceElementCollectionMethod extends ElementCollectionMethod {

	private static final String NOT_GLOBAL = "jrejs:!_this.isUseGlobalConnectionSettings().booleanValue()";
	private static final String IS_V2 = "jrejs:(_this.isSnmpV2() && !_this.isUseGlobalConnectionSettings().booleanValue())";
	private static final String IS_V3 = "jrejs:(_this.isSnmpV3() && !_this.isUseGlobalConnectionSettings().booleanValue())";

	private final Boolean useGlobalConnectionSettings;
	@NotNull(message = "The snmp version is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = NOT_GLOBAL)
	@CheckWith(value = SnmpVersionCheck.class, message = "The snmp version is invalid. It must be one of {validValues}.", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = NOT_GLOBAL)
	private final String snmpVersion;
	@NotNull(message = "The snmp port is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = NOT_GLOBAL)
	@Range(min = 1, max = 65535, message = "The snmp port must be a number between {min} and {max}", errorCode = ElementBodyErrorCodes.NUMBER_OUT_OF_RANGE, when = NOT_GLOBAL)
	private final Integer snmpPort;
	@NotNull(message = "The snmp v2 read community is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V2)
	@Length(max = 255, message = "The snmp v2 read community must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG, when = IS_V2)
	private final String snmpV2ReadCommunity;
	@NotNull(message = "The snmp v3 authentication username is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V3)
	@Length(max = 255, message = "The snmp v3 authentication username must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG, when = IS_V3)
	private final String snmpV3Username;
	@NotNull(message = "The snmp v3 authentication password is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V3)
	@Length(max = 255, message = "The snmp v3 authentication password must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG, when = IS_V3)
	private final String snmpV3AuthenticationPassword;
	@NotNull(message = "The snmp v3 authentication method is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V3)
	@CheckWith(value = SnmpV3AuthMethodCheck.class, message = "The snmp v3 authentication method is invalid. It must be one of {validValues}.", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V3)
	private final String snmpV3AuthenticationMethod;
	@NotNull(message = "The snmp v3 privacy password is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V3)
	@Length(max = 255, message = "The snmp v3 privacy password must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG, when = IS_V3)
	private final String snmpV3PrivacyPassword;
	@NotNull(message = "The snmp v3 privacy type is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V3)
	@CheckWith(value = SnmpV3PrivacyTypeCheck.class, message = "The snmp v3 privacy type is invalid. It must be one of {validValues}.", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = IS_V3)
	private final String snmpV3PrivacyType;
	@NotNull(message = "The isPingable field is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD, when = NOT_GLOBAL)
	private final Boolean isPingable;

	@JsonIgnore
	private final SnmpVersion snmpVersionEnum;
	@JsonIgnore
	private final SnmpV3AuthenticationMethod snmpV3AuthenticationMethodEnum;
	@JsonIgnore
	private final SnmpV3PrivacyType snmpV3PrivacyTypeEnum;

	@JsonCreator
	public AddNetworkDeviceElementCollectionMethod(
			@JsonProperty("useGlobalConnectionSettings") Boolean useGlobalConnectionSettings,
			@JsonProperty("snmpVersion") String snmpVersion, @JsonProperty("snmpPort") Integer snmpPort,
			@JsonProperty("snmpV2ReadCommunity") String snmpV2ReadCommunity,
			@JsonProperty("snmpV3Username") String snmpV3Username,
			@JsonProperty("snmpV3AuthenticationPassword") String snmpV3AuthenticationPassword,
			@JsonProperty("snmpV3AuthenticationMethod") String snmpV3AuthenticationMethod,
			@JsonProperty("snmpV3PrivacyPassword") String snmpV3PrivacyPassword,
			@JsonProperty("snmpV3PrivacyType") String snmpV3PrivacyType, @JsonProperty("isPingable") Boolean isPingable) {
		super(ElementConnectionTypeEnum.NetworkDevice);
		if (useGlobalConnectionSettings == null) {
			useGlobalConnectionSettings = false;
		}
		this.useGlobalConnectionSettings = useGlobalConnectionSettings;
		this.snmpVersion = snmpVersion;
		this.snmpPort = snmpPort;
		this.snmpV2ReadCommunity = snmpV2ReadCommunity;
		this.snmpV3Username = snmpV3Username;
		this.snmpV3AuthenticationPassword = snmpV3AuthenticationPassword;
		this.snmpV3AuthenticationMethod = snmpV3AuthenticationMethod;
		this.snmpV3PrivacyPassword = snmpV3PrivacyPassword;
		this.snmpV3PrivacyType = snmpV3PrivacyType;
		this.isPingable = isPingable;

		this.snmpVersionEnum = Enums.getIfPresent(SnmpVersion.class, Strings.nullToEmpty(snmpVersion)).orNull();
		this.snmpV3AuthenticationMethodEnum = Enums.getIfPresent(SnmpV3AuthenticationMethod.class,
				Strings.nullToEmpty(snmpV3AuthenticationMethod)).orNull();
		this.snmpV3PrivacyTypeEnum = Enums.getIfPresent(SnmpV3PrivacyType.class, Strings.nullToEmpty(snmpV3PrivacyType)).orNull();
	}

	public Boolean isUseGlobalConnectionSettings() {
		return useGlobalConnectionSettings;
	}

	public SnmpVersion getSnmpVersion() {
		return snmpVersionEnum;
	}

	public Integer getSnmpPort() {
		return snmpPort;
	}

	public String getSnmpV2ReadCommunity() {
		return snmpV2ReadCommunity;
	}

	public String getSnmpV3Username() {
		return snmpV3Username;
	}

	public String getSnmpV3AuthenticationPassword() {
		return snmpV3AuthenticationPassword;
	}

	public SnmpV3AuthenticationMethod getSnmpV3AuthenticationMethod() {
		return snmpV3AuthenticationMethodEnum;
	}

	public String getSnmpV3PrivacyPassword() {
		return snmpV3PrivacyPassword;
	}

	public SnmpV3PrivacyType getSnmpV3PrivacyType() {
		return snmpV3PrivacyTypeEnum;
	}

	public Boolean getIsPingable() {
		return isPingable;
	}

	@JsonIgnore
	public boolean isSnmpV2() {
		return snmpVersionEnum == SnmpVersion.v2;
	}

	@JsonIgnore
	public boolean isSnmpV3() {
		return snmpVersionEnum == SnmpVersion.v3;
	}

	private static class SnmpVersionCheck implements CheckWithCheck.SimpleCheckWithMessageVariables {

		@Override
		public boolean isSatisfied(Object validatedObject, Object value) {
			if (value == null) {
				return true;
			}
			AddNetworkDeviceElementCollectionMethod collectionMethod = (AddNetworkDeviceElementCollectionMethod) validatedObject;
			return collectionMethod.getSnmpVersion() != null;
		}

		@Override
		public Map<String, ? extends Serializable> createMessageVariables() {
			return AddNetworkDeviceElementCollectionMethod.createValidValues(SnmpVersion.class);
		}

	}

	private static class SnmpV3AuthMethodCheck implements CheckWithCheck.SimpleCheckWithMessageVariables {

		@Override
		public boolean isSatisfied(Object validatedObject, Object value) {
			if (value == null) {
				return true;
			}
			AddNetworkDeviceElementCollectionMethod collectionMethod = (AddNetworkDeviceElementCollectionMethod) validatedObject;
			return collectionMethod.getSnmpV3AuthenticationMethod() != null;
		}

		@Override
		public Map<String, ? extends Serializable> createMessageVariables() {
			return AddNetworkDeviceElementCollectionMethod.createValidValues(SnmpV3AuthenticationMethod.class);
		}

	}

	private static class SnmpV3PrivacyTypeCheck implements CheckWithCheck.SimpleCheckWithMessageVariables {

		@Override
		public boolean isSatisfied(Object validatedObject, Object value) {
			if (value == null) {
				return true;
			}
			AddNetworkDeviceElementCollectionMethod collectionMethod = (AddNetworkDeviceElementCollectionMethod) validatedObject;
			return collectionMethod.getSnmpV3PrivacyType() != null;
		}

		@Override
		public Map<String, ? extends Serializable> createMessageVariables() {
			return AddNetworkDeviceElementCollectionMethod.createValidValues(SnmpV3PrivacyType.class);
		}

	}

	private static <E extends Enum<E>> Map<String, ? extends Serializable> createValidValues(Class<E> clazz) {
		return ImmutableMap.of("validValues", EnumSet.allOf(clazz).toString());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), isPingable, snmpPort, snmpV2ReadCommunity, snmpV3Username,
				snmpV3AuthenticationMethod, snmpV3AuthenticationPassword, snmpV3PrivacyPassword, snmpV3PrivacyType, snmpVersion);
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
		AddNetworkDeviceElementCollectionMethod other = (AddNetworkDeviceElementCollectionMethod) obj;
		return Objects.equals(isPingable, other.isPingable) && Objects.equals(snmpPort, other.snmpPort)
				&& Objects.equals(snmpV2ReadCommunity, other.snmpV2ReadCommunity)
				&& Objects.equals(snmpV3Username, other.snmpV3Username)
				&& Objects.equals(snmpV3AuthenticationMethod, other.snmpV3AuthenticationMethod)
				&& Objects.equals(snmpV3AuthenticationPassword, other.snmpV3AuthenticationPassword)
				&& Objects.equals(snmpV3PrivacyPassword, other.snmpV3PrivacyPassword)
				&& Objects.equals(snmpV3PrivacyType, other.snmpV3PrivacyType) && Objects.equals(snmpVersion, other.snmpVersion);
	}

	@Override
	protected ToStringHelper toStringHelper() {
		return super.toStringHelper().add("useGlobalConnectionSettings", useGlobalConnectionSettings)
				.add("snmpVersion", snmpVersion).add("snmpPort", snmpPort).add("snmpV2ReadCommunity", snmpV2ReadCommunity)
				.add("snmpV3AuthenticationMethod", snmpV3AuthenticationMethod).add("snmpV3Username", snmpV3Username)
				.add("snmpV3AuthenticationPassword", snmpV3AuthenticationPassword).add("snmpV3PrivacyType", snmpV3PrivacyType)
				.add("snmpV3PrivacyPassword", snmpV3PrivacyPassword);
	}

}
