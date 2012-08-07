package com.uptimesoftware.business.monitors;

import java.util.regex.Pattern;

public class MonitorTypeFormatter {

	private static final Pattern LegacySnmpPattern = Pattern.compile("^SNMP[0-9]+$");
	private static final Pattern SnmpPollerPattern = Pattern.compile("^SNMP Poller [0-9]+$");

	public String getFormattedName(String monitorTypeName) {
		if (LegacySnmpPattern.matcher(monitorTypeName).matches()) {
			return "SNMP";
		}
		if (SnmpPollerPattern.matcher(monitorTypeName).matches()) {
			return "SNMP Poller";
		}
		return monitorTypeName;
	}

}
