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
	Application(ElementTypeEnum.Application, "Application"),
	JavaApp(ElementTypeEnum.JavaApp, "JavaApp"),
	JavaAppInstance(ElementTypeEnum.JavaAppInstance, "JavaAppInstance");

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

	public static ElementSubTypeEnum fromEntityData(EntityTypeEnum entityType, EntitySubTypeEnum entitySubType, String arch,
			String osver) {
		if (ElementSubTypeEnum.isNetworkType(entityType, entitySubType)) {
			return Switch;
		}
		if (ElementSubTypeEnum.isApplicationType(entityType)) {
			return Application;
		}
		if (ElementSubTypeEnum.isServerType(entityType, entitySubType)) {
			return ElementSubTypeEnum.getServerSubType(entitySubType, arch, osver);
		}
		if (ElementSubTypeEnum.isJavaApplicationType(entityType)) {
			return JavaApp;
		}
		if (ElementSubTypeEnum.isJavaApplicationInsType(entityType)) {
			return JavaAppInstance;
		}
		// if we don't know the parent type, then this subtype is irrelevent
		return null;
	}

	static private boolean isNetworkType(EntityTypeEnum entityType, EntitySubTypeEnum entitySubType) {
		return EntityTypeEnum.Node == entityType && entitySubType != EntitySubTypeEnum.VirtualNode;
	}

	static private boolean isApplicationType(EntityTypeEnum entityType) {
		return EntityTypeEnum.Application == entityType;
	}

	static private boolean isServerType(EntityTypeEnum entityType, EntitySubTypeEnum entitySubType) {
		return EntityTypeEnum.System == entityType
				|| (EntityTypeEnum.Node == entityType && EntitySubTypeEnum.VirtualNode == entitySubType)
				|| (EntityTypeEnum.VmwareObject == entityType && !entitySubType.isVmwareGroup());
	}
	
	static private boolean isJavaApplicationType(EntityTypeEnum entityType) {
		return EntityTypeEnum.JavaApp == entityType;
	}
	
	static private boolean isJavaApplicationInsType(EntityTypeEnum entityType) {
		return EntityTypeEnum.JavaAppInstance == entityType;
	}

	static private ElementSubTypeEnum getServerSubTypeFromOs(String arch, String osver) {
		OsType osType = OsType.fromArchAndOsver(arch, osver);
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

		case Novell:
			return Netware;

		default:
			break;
		}
		return Unknown;
	}

	static private ElementSubTypeEnum getServerSubType(EntitySubTypeEnum entitySubType, String arch, String osver) {
		switch (entitySubType) {
		case Agent:
		case WmiAgentless:
		case SnmpV2:
		case SnmpV3:
		case VirtualMachine:
			return ElementSubTypeEnum.getServerSubTypeFromOs(arch, osver);

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