package com.uptimesoftware.business.os;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OsTypeTest {

	@Test
	public void testDoesntFailWithNull() {
		assertEquals(OsType.Unknown, OsType.detectAgentOrWmi(null, null));
	}

	@Test
	public void testDoesntFailWithNullAndEmpty1() {
		assertEquals(OsType.Unknown, OsType.detectAgentOrWmi(null, ""));
	}

	@Test
	public void testDoesntFailWithNullAndEmpty2() {
		assertEquals(OsType.Unknown, OsType.detectAgentOrWmi("", null));
	}

	@Test
	public void testDoesntFailEmpty() {
		assertEquals(OsType.Unknown, OsType.detectAgentOrWmi("", ""));
	}

	@Test
	public void testParseUnknown() {
		assertEquals(OsType.Unknown, OsType.detectAgentOrWmi("bogus", "bogus"));
	}

	@Test
	public void testWindows2K3Server() {
		assertEquals(OsType.Windows, OsType.detectAgentOrWmi("Windows_NT x86 Family 15 Model 4 Stepping 1, GenuineIntel", "3790"));
	}

	@Test
	public void testWindowsNT() {
		assertEquals(OsType.Windows, OsType.detectAgentOrWmi("Windows_NT x86 Family 15 Model 4 Stepping 1, GenuineIntel", "2600"));
	}

	@Test
	public void testWindowsWmi() {
		assertEquals(OsType.Windows,
				OsType.detectAgentOrWmi("x86 Family 15 Model 4 Stepping 1, GenuineIntel", "Microsoft Windows 7 Professional "));
	}

	@Test
	public void testElinux() {
		assertEquals(OsType.Linux, OsType.detectAgentOrWmi(
				"Linux elinux 2.6.9-5.EL #1 Wed Jan 5 19:22:18 EST 2005 i686 i686 i386 GNU/Linux",
				"RedHat 4(Nahant 2.6.9-5.EL i686)"));
	}

	@Test
	public void testLinux() {
		assertEquals(OsType.Linux,
				OsType.detectAgentOrWmi("Linux systemname 2.4.21-4.ELsmp #1 SMP Fri Oct 3 17:52:56 EDT 2003 i686", ""));
	}

	@Test
	public void testLinuxSnmp() {
		assertEquals(OsType.Linux,
				OsType.detectAgentOrWmi("Linux systemname 2.4.21-4.ELsmp #1 SMP Fri Oct 3 17:52:56 EDT 2003 i686", null));
	}

	@Test
	public void testSunOSFilter() {
		assertEquals(OsType.Solaris,
				OsType.detectAgentOrWmi("SunOS filter 5.9 Generic_118558-03 sun4u sparc SUNW,Ultra-Enterprise", "5.9"));
	}

	@Test
	public void testFilterSNMP() {
		assertEquals(OsType.Solaris, OsType.detectAgentOrWmi("SunOS filter 5.9 Generic_118558-03 sun4u", null));
	}

	@Test
	public void testAix5l() {
		assertEquals(OsType.AIX, OsType.detectAgentOrWmi("AIX aix5l 1 5 0032501F4C00", "5.1"));
	}

	@Test
	public void testTru64() {
		assertEquals(OsType.Tru64, OsType.detectAgentOrWmi("OSF1 tru64 V5.1 2650 alpha", "V5.1"));
	}

	@Test
	public void testHPUX() {
		assertEquals(OsType.HPUX, OsType.detectAgentOrWmi("HP-UX hpux B.11.00 A 9000/780 2009585264 two-user license", "B.11.00"));
	}

	@Test
	public void testHPInteg() {
		assertEquals(OsType.HPUX,
				OsType.detectAgentOrWmi("HP-UX hpinteg B.11.23 U ia64 1498060259 unlimited-user license", "B.11.23"));
	}

}
