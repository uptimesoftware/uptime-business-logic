package com.uptimesoftware.business.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ElementSubTypeEnumTest {

	@Test
	public void netSwitch() {
		assertEquals(ElementSubTypeEnum.Switch, ElementSubTypeEnum.fromElementConfiguration(SystemType.Node, null, null, null));
	}

	@Test
	public void application() {
		assertEquals(ElementSubTypeEnum.Application,
				ElementSubTypeEnum.fromElementConfiguration(SystemType.Application, null, null, null));
	}

	@Test
	public void windows() {
		assertEquals(ElementSubTypeEnum.Windows, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.Agent, "Windows_NT Intel64 Family 6 Model 30 Stepping 5, GenuineIntel", "7600"));
		assertEquals(ElementSubTypeEnum.Windows, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.WmiAgentless, "Intel64 Family 6 Model 30 Stepping 5, GenuineIntel",
				"Microsoft Windows 7 Professional "));
		assertEquals(ElementSubTypeEnum.Windows, ElementSubTypeEnum.fromElementConfiguration(SystemType.VmwareObject,
				SystemSubType.VirtualMachine, "Microsoft Windows Server 2008 (64-bit)", null));
	}

	@Test
	public void linux() {
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.Agent,
				"Linux rh-jacky 2.6.32-220.el6.x86_64 #1 SMP Wed Nov 9 08:03:13 EST 2011 x86_64 x86_64 x86_64 GNU/Linux",
				"RedHat 6.2(Santiago 2.6.32-220.el6.x86_64 x86_64)"));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.SnmpV2, "Linux qa-proxy 2.6.27-17-server #1 SMP Fri Mar 12 04:04:33 UTC 2010 i686", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromElementConfiguration(SystemType.VmwareObject,
				SystemSubType.VirtualMachine, "Suse Linux Enterprise 11 (64-bit)", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromElementConfiguration(SystemType.VmwareObject,
				SystemSubType.VirtualMachine, "Other Linux (32-bit)", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromElementConfiguration(SystemType.VmwareObject,
				SystemSubType.VirtualMachine, "Red Hat Enterprise Linux 3 (32-bit)", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromElementConfiguration(SystemType.VmwareObject,
				SystemSubType.VirtualMachine, "Ubuntu Linux (32-bit)", null));
	}

	@Test
	public void novell() {
		assertEquals(ElementSubTypeEnum.Netware, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.NovellNrm, "Server Version 5.70.05, December 16, 2005", null));
	}

	@Test
	public void aix() {
		assertEquals(ElementSubTypeEnum.Aix, ElementSubTypeEnum.fromElementConfiguration(SystemType.System, SystemSubType.Agent,
				"AIX qa-aix61 1 6 00CA027D4C00", "6.1"));
	}

	@Test
	public void solaris() {
		assertEquals(ElementSubTypeEnum.Solaris, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.Agent, "SunOS qa-sol10-x86 5.10 Generic_127128-11 i86pc i386 i86pc", "5.10"));
		assertEquals(ElementSubTypeEnum.Solaris, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.SnmpV2, "SunOS filter 5.9 Generic_118558-03 sun4u", null));
	}

	@Test
	public void hpux() {
		assertEquals(ElementSubTypeEnum.Hpux, ElementSubTypeEnum.fromElementConfiguration(SystemType.System, SystemSubType.Agent,
				"HP-UX hpinteg B.11.23 U ia64 1498060259 unlimited-user license", "B.11.23"));
	}

	@Test
	public void hostSystem() {
		assertEquals(ElementSubTypeEnum.vCenterHostSystem, ElementSubTypeEnum.fromElementConfiguration(SystemType.VmwareObject,
				SystemSubType.HostSystem, "VMware ESXi 4.1.0 build-721871", "VMware ESXi 4.1.0 build-721871"));
	}

	@Test
	public void vCenter() {
		assertEquals(ElementSubTypeEnum.vCenterServer, ElementSubTypeEnum.fromElementConfiguration(SystemType.VmwareObject,
				SystemSubType.VirtualCenter, "VMware ESXi 4.1.0 build-721871", "VMware ESXi 4.1.0 build-721871"));
	}

	@Test
	public void esxServer() {
		assertEquals(ElementSubTypeEnum.EsxServer, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.VmwareEsx, "VMware ESXi 5.0.0 build-768111", "VMware ESXi 5.0.0 build-768111"));
	}

	@Test
	public void ibmPower() {
		assertEquals(ElementSubTypeEnum.IbmPowerSystems, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.LparHmc, "9110-51A", "HMC v7 R7.1.0.2 build 20100510.1"));
	}

	@Test
	public void unknown() {
		assertEquals(ElementSubTypeEnum.Unknown, ElementSubTypeEnum.fromElementConfiguration(SystemType.System,
				SystemSubType.Agent, "OSF1 tru64 V5.1 2650 alpha", "V5.1"));
	}

}
