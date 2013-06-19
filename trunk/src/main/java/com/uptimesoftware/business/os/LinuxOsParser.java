package com.uptimesoftware.business.os;

class LinuxOsParser implements OsParser {

	private static final OsInfo DEFAULT = new OsInfo("Linux", "");

	@Override
	public OsInfo parse(String arch, String osver) {
		Architecture architecture = Architecture.getArchitecture(arch);
		if (osver == null || osver.isEmpty()) {
			return new OsInfo(DEFAULT.getOsType(), DEFAULT.getOsVersion(), architecture);
		}
		int firstParen = osver.indexOf('(');
		if (firstParen > 0) {
			osver = osver.substring(0, firstParen).trim();
		}
		String[] osTypeAndVersion = osver.split("\\s+", 2);
		String osType = osTypeAndVersion[0];
		String osVersion = osTypeAndVersion.length == 2 ? osTypeAndVersion[1] : "";
		if ("RedHat".equals(osType)) {
			return new RedHatOsInfo("Linux", osVersion, architecture);
		}
		if ("SUSE".equals(osType)) {
			return new SuseOsInfo("Linux", osVersion, architecture);
		}

		return new UnknownLinuxOsInfo("Linux", osver, architecture);
	}

}
