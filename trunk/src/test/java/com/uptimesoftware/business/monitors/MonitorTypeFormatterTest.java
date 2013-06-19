package com.uptimesoftware.business.monitors;

import static org.junit.Assert.*;
import org.junit.Test;

public class MonitorTypeFormatterTest {
	
	private final MonitorTypeFormatter formatter = new MonitorTypeFormatter();

	@Test
	public void nullThrowsException() {
		try {
			formatter.getFormattedName(null);
			fail("Expected NullPointerException");
		} catch (NullPointerException e) {
			// expected
		}
	}
	
	@Test
	public void formatsLegacySnmp() {
		assertEquals("SNMP", formatter.getFormattedName("SNMP123"));
		assertEquals("SNMP", formatter.getFormattedName("SNMP"));
		assertEquals("SNMP123 !", formatter.getFormattedName("SNMP123 !"));
		assertEquals("! SNMP123", formatter.getFormattedName("! SNMP123"));
	}

	@Test
	public void formatsSnmpPoller() {
		assertEquals("SNMP Poller", formatter.getFormattedName("SNMP Poller 123"));
		assertEquals("SNMP Poller", formatter.getFormattedName("SNMP Poller"));
		assertEquals("SNMP Poller 123 !", formatter.getFormattedName("SNMP Poller 123 !"));
		assertEquals("! SNMP Poller 123", formatter.getFormattedName("! SNMP Poller 123"));
	}

	@Test
	public void returnsOtherTypesUnformatted() {
		assertEquals("Ping", formatter.getFormattedName("Ping"));
		assertEquals("Process Count Check", formatter.getFormattedName("Process Count Check"));
		assertEquals("Custom Monitor", formatter.getFormattedName("Custom Monitor"));

	}

}
