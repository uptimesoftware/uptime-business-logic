package com.uptimesoftware.business.os;

class SolarisOsParser implements OsParser {

	private static final OsInfo DEFAULT = new OsInfo("Solaris", "");
	private static final OsInfo Solaris8 = new OsInfo("Solaris", "8");
	private static final OsInfo Solaris9 = new OsInfo("Solaris", "9");
	private static final OsInfo Solaris10 = new OsInfo("Solaris", "10");

	@Override
	public OsInfo parse(String arch, String osver) {
		Architecture architecture = Architecture.getArchitecture(arch);
		if (arch == null) {
			return createOsInfo(DEFAULT, architecture);
		}

		if ("5.8".equals(osver)) {
			return createOsInfo(Solaris8, architecture);
		} else if ("5.9".equals(osver)) {
			return createOsInfo(Solaris9, architecture);
		} else if ("5.10".equals(osver)) {
			return createOsInfo(Solaris10, architecture);
		}

		if (arch.contains("5.8")) {
			return createOsInfo(Solaris8, architecture);
		} else if (arch.contains("5.9")) {
			return createOsInfo(Solaris9, architecture);
		} else if (arch.contains("5.10")) {
			return createOsInfo(Solaris10, architecture);
		}

		return createOsInfo(DEFAULT, architecture);
	}

	private OsInfo createOsInfo(OsInfo info, Architecture arch) {
		return new OsInfo(info.getOsType(), info.getOsVersion(), arch);
	}

}
