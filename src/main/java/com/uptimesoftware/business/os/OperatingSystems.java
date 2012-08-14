package com.uptimesoftware.business.os;

import com.google.common.base.Strings;
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

	public static String getFullOsName(EntitySubTypeEnum entitySubType, String arch, String osver) {
		if (entitySubType == EntitySubTypeEnum.LparHmc) {
			return osver;
		}
		return getOsInfo(entitySubType, arch, osver).getOsFull();
	}

	public static String getFullOsNameFromWindowsVersionNumber(String windowsVersionNumber) {
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
		if (entitySubType == null) {
			return UNKNOWN;
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
		return getOsInfoFromArchAndOsver(arch, osver);
	}

	private static OsInfo getVmwareGuestOsInfo(String arch) {
		if (Strings.isNullOrEmpty(arch)) {
			return UNKNOWN;
		}
		return vmwareGuestParser.parse(arch, null);
	}

	private static OsInfo getOsInfoFromArchAndOsver(String arch, String osver) {
		if (Strings.isNullOrEmpty(arch)) {
			return UNKNOWN;
		}

		OsType osType = OsType.fromArchAndOsver(arch, osver);
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

	private static OsInfo getVirtualCenterOsInfo(String virtualCenterSettingsApiVersion) {
		return new OsInfo("vCenter", "VMware " + virtualCenterSettingsApiVersion);
	}

	private static OsInfo getNovellNrmOsInfo(String arch) {
		return new OsInfo("Novell", arch, Architecture.x86);
	}

	private static OsInfo getLparHmcServerOsInfo(String osver) {
		return new OsInfo("IBM Power Systems", osver, Architecture.Power);
	}

	private static OsInfo getLparVioServerOsInfo(String arch, String osver) {
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

}
