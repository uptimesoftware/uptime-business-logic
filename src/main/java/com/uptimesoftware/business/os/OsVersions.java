package com.uptimesoftware.business.os;

import com.google.common.base.Enums;
import com.google.common.base.Strings;

public class OsVersions {

	public enum UptimeDefinedOsVersion {
		VirtualMachine,
		VirtualCenter;
	}

	public static boolean isUptimeDefined(String osver) {
		return Enums.getIfPresent(UptimeDefinedOsVersion.class, Strings.nullToEmpty(osver)).isPresent();
	}

	public static UptimeDefinedOsVersion getUptimeDefined(String osver) {
		return Enums.getIfPresent(UptimeDefinedOsVersion.class, Strings.nullToEmpty(osver)).orNull();
	}

}
