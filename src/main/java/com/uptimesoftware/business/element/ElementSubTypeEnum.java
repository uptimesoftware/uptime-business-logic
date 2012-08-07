package com.uptimesoftware.business.element;

import com.uptimesoftware.business.os.OsType;

public enum ElementSubTypeEnum {
	Aix(ElementTypeEnum.Server, "IBM AIX"),
	Linux(ElementTypeEnum.Server, "Linux"),
	Netware(ElementTypeEnum.Server, "Novell Netware"),
	Solaris(ElementTypeEnum.Server, "Oracle Solaris"),
	Hpux(ElementTypeEnum.Server, "HP-UX"),
	Windows(ElementTypeEnum.Server, "Microsoft Windows"),
	EsxServer(ElementTypeEnum.Server, "VMware ESX Server"),
	IbmPowerSystems(ElementTypeEnum.Server, "IBM Power Systems"),
	vCenterServer(ElementTypeEnum.Server, "VMware vCenter Server"),
	vCenterHostSystem(ElementTypeEnum.Server, "VMware vSphere Server"),
	Unknown(ElementTypeEnum.Server, "Unknown"),
	Switch(ElementTypeEnum.NetworkDevice, "Switch"),
	Application(ElementTypeEnum.Application, "Application");

	private final ElementTypeEnum elementType;
	private final String defaultName;

	private ElementSubTypeEnum(ElementTypeEnum elementType, String defaultName) {
		this.elementType = elementType;
		this.defaultName = defaultName;
	}

	public ElementTypeEnum getElementType() {
		return elementType;
	}

	public String getDefaultName() {
		return defaultName;
	}

	public static ElementSubTypeEnum fromElementConfiguration(SystemType systemType, SystemSubType systemSubType, String arch,
			String osver) {
		if (ElementSubTypeEnum.isNetworkType(systemType)) {
			return Switch;
		}
		if (ElementSubTypeEnum.isApplicationType(systemType)) {
			return Application;
		}
		return ElementSubTypeEnum.getServerSubType(systemSubType, arch, osver);
	}

	static private boolean isNetworkType(SystemType systemType) {
		return SystemType.Node == systemType;
	}

	static private boolean isApplicationType(SystemType systemType) {
		return SystemType.Application == systemType;
	}

	static private ElementSubTypeEnum getServerSubTypeFromOs(String arch, String osver) {
		OsType osType = OsType.detectAgentOrWmi(arch, osver);
		return toServerSubType(osType);
	}

	static private ElementSubTypeEnum toServerSubType(OsType osType) {
		switch (osType) {
		case Windows:
			return Windows;

		case Linux:
			return Linux;

		case Solaris:
			return Solaris;

		case AIX:
			return Aix;

		case HPUX:
			return Hpux;

		default:
			break;
		}
		return Unknown;
	}

	static private ElementSubTypeEnum getServerSubTypeFromVmOs(String arch) {
		OsType osType = OsType.detectVirtualMachineGuest(arch);
		return ElementSubTypeEnum.toServerSubType(osType);
	}

	static private ElementSubTypeEnum getServerSubType(SystemSubType systemSubType, String arch, String osver) {
		switch (systemSubType) {
		case Agent:
		case WmiAgentless:
		case SnmpV2:
		case SnmpV3:
			return ElementSubTypeEnum.getServerSubTypeFromOs(arch, osver);

		case VirtualMachine:
			return ElementSubTypeEnum.getServerSubTypeFromVmOs(arch);

		case NovellNrm:
			return Netware;

		case LparHmc:
		case LparVio:
			return IbmPowerSystems;

		case VirtualCenter:
			return vCenterServer;

		case HostSystem:
			return vCenterHostSystem;

		case VmwareEsx:
			return EsxServer;

		default:
			break;
		}
		return Unknown;
	}

}