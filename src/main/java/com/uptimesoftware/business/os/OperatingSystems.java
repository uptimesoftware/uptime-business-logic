package com.uptimesoftware.business.os;

import com.uptimesoftware.business.element.EntitySubTypeEnum;

public class OperatingSystems {

	public static final OsInfo UNKNOWN = new OsInfo("Unknown", "", Architecture.Unknown);

	private static final OsParser windowsParser = new WindowsOsParser();
	private static final OsParser linuxParser = new LinuxOsParser();
	private static final OsParser solarisParser = new SolarisOsParser();
	private static final OsParser tru64Parser = new Tru64OsParser();
	private static final OsParser aixParser = new AixOsParser();
	private static final OsParser hpuxParser = new HpUxOsParser();
	private static final OsParser vmwareGuestParser = new VmwareGuestOsParser();

	public static String getFullOsName(String arch, String osver) {
		if (isLparHmcServer(osver)) {
			return osver;
		}
		return getAgentOrWmiOsInfo(arch, osver).getOsFull();
	}

	public static String getWindowsOsNameFromVersionNumber(String windowsVersionNumber) {
		return windowsParser.parse(null, windowsVersionNumber).getOsFull();
	}

	public static boolean isWindowsVersionNumber(String versionNumber) {
		return WindowsOsParser.recognizedVersionNumbers().contains(versionNumber);
	}

	public static OsInfo getOsInfo(EntitySubTypeEnum entitySubType, String arch, String osver) {
		if (OsVersions.isUptimeDefined(osver)) {
			switch (OsVersions.getUptimeDefined(osver)) {
			case VirtualMachine:
				return getVmwareGuestOsInfo(arch);

			case VirtualCenter:
				return getVirtualCenterOsInfo(arch);

			default:
				break;
			}
		}
		switch (entitySubType) {
		case NetworkDevice:
			return new OsInfo(arch, "", Architecture.Unknown);

		case LparHmc:
			return getLparHmcServerOsInfo(osver);

		case LparVio:
			return getLparVioServerOsInfo(arch, osver);

		case NovellNrm:
			return getNovellNrmOsInfo(arch);

		case VirtualNode:
			return UNKNOWN;

		default:
			break;
		}
		return getAgentOrWmiOsInfo(arch, osver);
	}

	public static OsInfo getVmwareGuestOsInfo(String arch) {
		if (arch == null || arch.isEmpty()) {
			return UNKNOWN;
		}
		return vmwareGuestParser.parse(arch, null);
	}

	public static OsInfo getAgentOrWmiOsInfo(String arch, String osver) {
		if (arch == null || arch.isEmpty()) {
			return UNKNOWN;
		}

		OsType osType = OsType.detectAgentOrWmi(arch, osver);
		if (osType == OsType.Windows) {
			return windowsParser.parse(arch, osver);
		}
		if (osType == OsType.Linux) {
			return linuxParser.parse(arch, osver);
		}
		if (osType == OsType.Solaris) {
			return solarisParser.parse(arch, osver);
		}
		if (osType == OsType.Tru64) {
			return tru64Parser.parse(arch, osver);
		}
		if (osType == OsType.AIX) {
			return aixParser.parse(arch, osver);
		}
		if (osType == OsType.HPUX) {
			return hpuxParser.parse(arch, osver);
		}

		String firstWord = getFirstWord(arch);
		if (osver != null) {
			return new UnrecognizedOsInfo(firstWord, osver, Architecture.getArchitecture(arch));
		}
		return new OsInfo(removeUnderscores(arch), "", Architecture.getArchitecture(arch));
	}

	public static OsInfo getVirtualCenterOsInfo(String virtualCenterSettingsApiVersion) {
		return new OsInfo("vCenter", "VMware " + virtualCenterSettingsApiVersion);
	}

	public static OsInfo getNovellNrmOsInfo(String arch) {
		return new OsInfo("Novell", arch, Architecture.x86);
	}

	public static OsInfo getLparHmcServerOsInfo(String osver) {
		return new OsInfo("IBM Power Systems", osver, Architecture.Power);
	}

	public static OsInfo getLparVioServerOsInfo(String arch, String osver) {
		String firstWord = getFirstWord(arch);
		return new OsInfo("IBM Power Systems", firstWord + " " + osver, Architecture.Power);
	}

	private static String getFirstWord(String arch) {
		String systemType = arch;
		int firstSpace = systemType.indexOf(' ');
		if (firstSpace != -1) {
			systemType = arch.substring(0, firstSpace);
		}
		return removeUnderscores(systemType);
	}

	private static String removeUnderscores(String string) {
		if (string != null) {
			return string.replace("_", " ");
		}
		return "";
	}

	private static boolean isLparHmcServer(String osver) {
		return osver != null && osver.startsWith("HMC");
	}

}
