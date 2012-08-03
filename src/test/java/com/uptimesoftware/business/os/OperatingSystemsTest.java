package com.uptimesoftware.business.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OperatingSystemsTest {

	@Test
	public void testParseUnknown() {
		assertEquals("ar ch - osver", OperatingSystems.getFullOsName("ar_ch", "osver"));
		assertEquals("arch", OperatingSystems.getFullOsName("arch", null));
		assertEquals(Architecture.Unknown, OperatingSystems.getAgentOrWmiOsInfo("ar_ch", "osver").getArch());
	}

	@Test
	public void testNullCases() {
		assertEquals("Unknown", OperatingSystems.getFullOsName(null, null));
		assertEquals("Unknown", OperatingSystems.getFullOsName("", null));
		assertEquals("Unknown", OperatingSystems.getFullOsName(null, ""));
		assertEquals("Linux", new LinuxOsParser().parse(null, null).getOsFull());
		assertEquals("Solaris", new SolarisOsParser().parse(null, null).getOsFull());
		assertEquals("Windows", new WindowsOsParser().parse(null, null).getOsFull());
		assertEquals("AIX", new AixOsParser().parse(null, null).getOsFull());
		assertEquals("HP-UX", new HpUxOsParser().parse(null, null).getOsFull());
		assertEquals("Tru64", new Tru64OsParser().parse(null, null).getOsFull());
	}

	@Test
	public void testUnderscoresAreRemoved() {
		assertEquals("under score", OperatingSystems.getFullOsName("under_score", null));
	}

	@Test
	public void testLinux() {
		assertEquals("Red Hat Linux 3", OperatingSystems.getFullOsName("Linux", "RedHat 3(Taroon 2.4.21-4.ELsmp i686)"));
		assertEquals("Red Hat Linux 4",
				OperatingSystems.getFullOsName("Linux", "RedHat 4(Nahant Update 4 2.6.9-42.ELsmp x86_64)"));
		assertEquals("Red Hat Linux 5", OperatingSystems.getFullOsName("Linux", "RedHat 5(Tikanga 2.6.18-8.el5 i686)"));
		assertEquals("Debian testing/unstable",
				OperatingSystems.getFullOsName("Linux", "Debian testing/unstable ( 2.6.15-26-server i686)"));
		assertEquals("SUSE Linux Enterprise Desktop 10",
				OperatingSystems.getFullOsName("Linux", "SUSE Linux Enterprise Desktop 10 (i586)  10 ( 2.6.16.21-0.8-smp i686)"));

		OsInfo osinfo = OperatingSystems.getAgentOrWmiOsInfo(
				"Linux rh-lorne 2.6.32-131.0.15.el6.x86_64 #1 SMP Tue May 10 15:42:40 EDT 2011 x86_64 x86_64 x86_64 GNU/Linux",
				"RedHat 6.1(Santiago 2.6.32-131.0.15.el6.x86_64 x86_64)");
		assertEquals("Linux", osinfo.getOsType());
		assertEquals("Red Hat 6.1", osinfo.getOsVersion());
		assertEquals("x86_64", osinfo.getArchName());
	}

	@Test
	public void testWindows() {
		OsInfo osinfo = OperatingSystems.getAgentOrWmiOsInfo("Windows_NT Intel64 Family 6 Model 30 Stepping 5, GenuineIntel",
				"7600");
		assertEquals("Windows", osinfo.getOsType());
		assertEquals("7/Server 2008 R2", osinfo.getOsVersion());
		assertEquals("x86_64", osinfo.getArchName());
	}

	@Test
	public void testSolaris() {
		OsInfo osinfo = OperatingSystems
				.getAgentOrWmiOsInfo("SunOS qa-sol10-x86 5.10 Generic_127128-11 i86pc i386 i86pc", "5.10");
		assertEquals("Solaris", osinfo.getOsType());
		assertEquals("10", osinfo.getOsVersion());
		assertEquals("x86", osinfo.getArchName());
	}

	@Test
	public void testSnmp() {
		assertEquals("Solaris 9", OperatingSystems.getFullOsName("SunOS filter 5.9 Generic_118558-03 sun4u", null));
	}

	@Test
	public void testNovellShowsFullArchString6016() {
		assertEquals("Server Version 5.70.05, December 16, 2005",
				OperatingSystems.getFullOsName("Server Version 5.70.05, December 16, 2005", null));
	}

	@Test
	public void testDefaultOsWhenEmptyOsVersion() {
		assertEquals("Windows", OperatingSystems.getFullOsName("Windows_NT Intel64 Family", null));
		assertEquals("Windows", OperatingSystems.getFullOsName("Windows_NT Intel64 Family", ""));

		assertEquals("Linux", OperatingSystems.getFullOsName("Linux dev-rhes52.rd.local", null));
		assertEquals("Linux", OperatingSystems.getFullOsName("Linux dev-rhes52.rd.local", ""));

		assertEquals("Solaris", OperatingSystems.getFullOsName("SunOS dev1-sol10-sparc", ""));
		assertEquals("Solaris", OperatingSystems.getFullOsName("SunOS dev1-sol10-sparc", null));
	}

	@Test
	public void testDefaultOsWhenVersionNotOnFile() {
		assertEquals("Windows", OperatingSystems.getFullOsName("Windows_NT Intel64 Family", "9999"));

		assertEquals("Solaris", OperatingSystems.getFullOsName("SunOS dev1-sol10-sparc", "999.9"));
	}

	@Test
	public void testWindowsSp1BuildNumberGoesToOsName() {
		assertEquals("Windows 7/Server 2008 R2", OperatingSystems.getFullOsName("Windows_NT Intel64 Family", "7601"));
	}

	@Test
	public void testSunOs() {
		assertEquals("Solaris 8", OperatingSystems.getFullOsName("SunOS", "5.8"));
		assertEquals("Solaris 9", OperatingSystems.getFullOsName("SunOS", "5.9"));
		assertEquals("Solaris 10", OperatingSystems.getFullOsName("SunOS", "5.10"));
	}

	@Test
	public void testVmwareGuestArchitecture() {
		assertEquals(new OsInfo("Linux", "Red Hat Enterprise Linux 5", Architecture.x86_64),
				OperatingSystems.getVmwareGuestOsInfo("Red Hat Enterprise Linux 5 (64-bit)"));
	}

	@Test
	public void testWmi() {
		assertEquals("Windows 7 Professional", OperatingSystems.getFullOsName(
				"Intel64 Family 6 Model 30 Stepping 5, GenuineIntel", "Microsoft Windows 7 Professional "));
	}

	@Test
	public void testWindowsVersionNumberParsing() {
		assertEquals("Windows 7/Server 2008 R2", OperatingSystems.getWindowsOsNameFromVersionNumber("7600"));
		assertEquals("Windows", OperatingSystems.getWindowsOsNameFromVersionNumber("invalid input"));
		assertTrue(OperatingSystems.isWindowsVersionNumber("7600"));
		assertFalse(OperatingSystems.isWindowsVersionNumber("invalid input"));
	}

	@Test
	public void testTru64OsName() {
		assertEquals("Tru64 5.1", OperatingSystems.getFullOsName("OSF1 tru64 V5.1 2650 alpha", "V5.1"));
	}

	@Test
	public void testAixOsName() {
		assertEquals("AIX 5.3", OperatingSystems.getFullOsName("AIX aixvio 3 5 00CA027D4C00", "5.3"));
		assertEquals("AIX 6.1", OperatingSystems.getFullOsName("AIX qa-aix61 1 6 00CA027D4C00", "6.1"));
	}

	@Test
	public void testHpUxOsName() {
		assertEquals("HP-UX 11.23",
				OperatingSystems.getFullOsName("HP-UX hpinteg B.11.23 U ia64 1498060259 unlimited-user license", "B.11.23"));
	}

	@Test
	public void testLparHmcServerOsName() {
		assertEquals("HMC v7 R7.1.0.2 build 20100510.1",
				OperatingSystems.getFullOsName("9110-51A", "HMC v7 R7.1.0.2 build 20100510.1"));
	}

	@Test
	public void testIbmPowerSystems() {
		OsInfo osinfo = OperatingSystems.getLparHmcServerOsInfo("HMC v7 R7.1.0.2 build 20100510.1");
		assertEquals("IBM Power Systems", osinfo.getOsType());
		assertEquals("HMC v7 R7.1.0.2 build 20100510.1", osinfo.getOsVersion());
		assertEquals("Power", osinfo.getArchName());

		osinfo = OperatingSystems.getLparVioServerOsInfo("AIX aixvio 3 5 00CA027D4C00", "5.3");
		assertEquals("IBM Power Systems", osinfo.getOsType());
		assertEquals("AIX 5.3", osinfo.getOsVersion());
		assertEquals("Power", osinfo.getArchName());
	}

}