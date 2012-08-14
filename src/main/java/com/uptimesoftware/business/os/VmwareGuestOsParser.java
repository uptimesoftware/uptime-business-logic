package com.uptimesoftware.business.os;

class VmwareGuestOsParser implements OsParser {

	private static final String WindowsPrefix = "Microsoft Windows ";
	private static final String SolarisPrefix = "Sun Solaris ";
	private static final String NovellPrefix = "Novell ";

	@Override
	public OsInfo parse(String arch, String osver) {
		Architecture architecture = Architecture.getArchitecture(arch);
		if (architecture == Architecture.Unknown) {
			architecture = Architecture.x86;
		}
		arch = removeProcessorType(arch);
		String osType = getOsType(arch);
		String osVersion = getOsVersion(arch);
		return new OsInfo(osType, osVersion, architecture);
	}

	private String getOsType(String arch) {
		OsType vmOsType = OsType.fromVirtualMachineArchitecture(arch);
		String osType = vmOsType.name();
		if (vmOsType == OsType.Unknown) {
			String[] words = arch.split("\\s+", 2);
			return words[0];
		}
		return osType;
	}

	private String getOsVersion(String arch) {
		if (arch.startsWith(WindowsPrefix)) {
			return arch.substring(WindowsPrefix.length());
		}
		if (arch.startsWith(SolarisPrefix)) {
			return arch.substring(SolarisPrefix.length());
		}
		if (arch.startsWith(NovellPrefix)) {
			return arch.substring(NovellPrefix.length());
		}
		return arch;
	}

	private String removeProcessorType(String arch) {
		if (arch.endsWith("-bit)")) {
			return arch.substring(0, arch.length() - 8).trim();
		}
		return arch;
	}

}
