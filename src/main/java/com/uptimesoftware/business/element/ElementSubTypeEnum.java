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
	VcenterServer(ElementTypeEnum.Server, "VMware vCenter Server"),
	VcenterHostSystem(ElementTypeEnum.Server, "VMware vSphere Server"),
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

	public static ElementSubTypeEnum fromEntityData(EntityTypeEnum systemType, EntitySubTypeEnum systemSubType, String arch,
			String osver) {
		if (ElementSubTypeEnum.isNetworkType(systemType, systemSubType)) {
			return Switch;
		}
		if (ElementSubTypeEnum.isApplicationType(systemType)) {
			return Application;
		}
		if (ElementSubTypeEnum.isServerType(systemType, systemSubType)) {
			return ElementSubTypeEnum.getServerSubType(systemSubType, arch, osver);
		}
		// if we don't know the parent type, then this subtype is irrelevent
		return null;
	}

	static private boolean isNetworkType(EntityTypeEnum systemType, EntitySubTypeEnum systemSubType) {
		return EntityTypeEnum.Node == systemType && systemSubType != EntitySubTypeEnum.VirtualNode;
	}

	static private boolean isApplicationType(EntityTypeEnum systemType) {
		return EntityTypeEnum.Application == systemType;
	}

	static private boolean isServerType(EntityTypeEnum systemType, EntitySubTypeEnum systemSubType) {
		return EntityTypeEnum.System == systemType
				|| (EntityTypeEnum.Node == systemType && EntitySubTypeEnum.VirtualNode == systemSubType)
				|| (EntityTypeEnum.VmwareObject == systemType && !systemSubType.isVmwareGroup());
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

	static private ElementSubTypeEnum getServerSubType(EntitySubTypeEnum systemSubType, String arch, String osver) {
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
			return VcenterServer;

		case HostSystem:
			return VcenterHostSystem;

		case VmwareEsx:
			return EsxServer;

		default:
			break;
		}
		return Unknown;
	}

}