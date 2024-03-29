package com.uptimesoftware.business.os;

class SuseOsInfo extends OsInfo {

	SuseOsInfo(String osType, String osVersion, Architecture arch) {
		super(osType, osVersion, arch);
	}

	@Override
	public String getOsFull() {
		return "SUSE " + super.getOsVersion();
	}

}
