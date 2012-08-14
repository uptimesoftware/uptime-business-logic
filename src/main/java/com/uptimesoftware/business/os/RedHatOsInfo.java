package com.uptimesoftware.business.os;

class RedHatOsInfo extends OsInfo {

	RedHatOsInfo(String osType, String osVersion, Architecture arch) {
		super(osType, osVersion, arch);
	}

	@Override
	public String getOsFull() {
		return "Red Hat Linux " + super.getOsVersion();
	}

	@Override
	public String getOsVersion() {
		return "Red Hat " + super.getOsVersion();
	}

}
