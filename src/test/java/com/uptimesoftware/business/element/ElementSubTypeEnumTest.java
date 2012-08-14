package com.uptimesoftware.business.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ElementSubTypeEnumTest {

	@Test
	public void netSwitch() {
		assertEquals(ElementSubTypeEnum.Switch, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.Node, EntitySubTypeEnum.NetworkDevice, "Neyland 24T", null));
	}

	@Test
	public void application() {
		assertEquals(ElementSubTypeEnum.Application,
				ElementSubTypeEnum.fromEntityData(EntityTypeEnum.Application, null, null, null));
	}

	@Test
	public void windows() {
		assertEquals(ElementSubTypeEnum.Windows, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.Agent, "Windows_NT Intel64 Family 6 Model 30 Stepping 5, GenuineIntel", "7600"));
		assertEquals(ElementSubTypeEnum.Windows, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.WmiAgentless, "Intel64 Family 6 Model 30 Stepping 5, GenuineIntel",
				"Microsoft Windows 7 Professional "));
		assertEquals(ElementSubTypeEnum.Windows, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject,
				EntitySubTypeEnum.VirtualMachine, "Microsoft Windows Server 2008 (64-bit)", null));
	}

	@Test
	public void linux() {
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.Agent,
				"Linux rh-jacky 2.6.32-220.el6.x86_64 #1 SMP Wed Nov 9 08:03:13 EST 2011 x86_64 x86_64 x86_64 GNU/Linux",
				"RedHat 6.2(Santiago 2.6.32-220.el6.x86_64 x86_64)"));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.SnmpV2, "Linux qa-proxy 2.6.27-17-server #1 SMP Fri Mar 12 04:04:33 UTC 2010 i686", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject,
				EntitySubTypeEnum.VirtualMachine, "Suse Linux Enterprise 11 (64-bit)", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject,
				EntitySubTypeEnum.VirtualMachine, "Other Linux (32-bit)", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject,
				EntitySubTypeEnum.VirtualMachine, "Red Hat Enterprise Linux 3 (32-bit)", null));
		assertEquals(ElementSubTypeEnum.Linux, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject,
				EntitySubTypeEnum.VirtualMachine, "Ubuntu Linux (32-bit)", null));
	}

	@Test
	public void novell() {
		assertEquals(ElementSubTypeEnum.Netware, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.NovellNrm, "Server Version 5.70.05, December 16, 2005", null));
	}

	@Test
	public void aix() {
		assertEquals(ElementSubTypeEnum.Aix, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System, EntitySubTypeEnum.Agent,
				"AIX qa-aix61 1 6 00CA027D4C00", "6.1"));
	}

	@Test
	public void solaris() {
		assertEquals(ElementSubTypeEnum.Solaris, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.Agent, "SunOS qa-sol10-x86 5.10 Generic_127128-11 i86pc i386 i86pc", "5.10"));
		assertEquals(ElementSubTypeEnum.Solaris, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.SnmpV2, "SunOS filter 5.9 Generic_118558-03 sun4u", null));
	}

	@Test
	public void hpux() {
		assertEquals(ElementSubTypeEnum.Hpux, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System, EntitySubTypeEnum.Agent,
				"HP-UX hpinteg B.11.23 U ia64 1498060259 unlimited-user license", "B.11.23"));
	}

	@Test
	public void hostSystem() {
		assertEquals(ElementSubTypeEnum.VcenterHostSystem, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject,
				EntitySubTypeEnum.HostSystem, "VMware ESXi 4.1.0 build-721871", "VMware ESXi 4.1.0 build-721871"));
	}

	@Test
	public void vCenter() {
		assertEquals(ElementSubTypeEnum.VcenterServer, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject,
				EntitySubTypeEnum.VirtualCenter, "VMware ESXi 4.1.0 build-721871", "VMware ESXi 4.1.0 build-721871"));
	}

	@Test
	public void esxServer() {
		assertEquals(ElementSubTypeEnum.EsxServer, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.VmwareEsx, "VMware ESXi 5.0.0 build-768111", "VMware ESXi 5.0.0 build-768111"));
	}

	@Test
	public void ibmPower() {
		assertEquals(ElementSubTypeEnum.IbmPowerSystems, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.LparHmc, "9110-51A", "HMC v7 R7.1.0.2 build 20100510.1"));
	}

	@Test
	public void unknown() {
		assertEquals(ElementSubTypeEnum.Unknown, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.System,
				EntitySubTypeEnum.Agent, "OSF1 tru64 V5.1 2650 alpha", "V5.1"));
	}
	
	@Test
	public void vnode() {
		assertEquals(ElementSubTypeEnum.Unknown, ElementSubTypeEnum.fromEntityData(EntityTypeEnum.Node, EntitySubTypeEnum.VirtualNode, null, null));
	}
	
	@Test
	public void sla() {
		assertNull(ElementSubTypeEnum.fromEntityData(EntityTypeEnum.ServiceLevelAgreement, null, null, null));
	}
	
	@Test
	public void vcenterGroups() {
		assertNull(ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject, EntitySubTypeEnum.Cluster, null, null));
		assertNull(ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject, EntitySubTypeEnum.ComputeResource, null, null));
		assertNull(ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject, EntitySubTypeEnum.ResourcePool, null, null));
		assertNull(ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject, EntitySubTypeEnum.VirtualApp, null, null));
		assertNull(ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject, EntitySubTypeEnum.VmwareDatacenter, null, null));
		assertNull(ElementSubTypeEnum.fromEntityData(EntityTypeEnum.VmwareObject, EntitySubTypeEnum.VmwareFolder, null, null));
	}
	
}
