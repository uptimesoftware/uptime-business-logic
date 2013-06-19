package com.uptimesoftware.business.os;

class UnknownLinuxOsInfo extends OsInfo {

	UnknownLinuxOsInfo(String osType, String osVersion, Architecture arch) {
		super(osType, osVersion, arch);
	}

	@Override
	public String getOsFull() {
		return super.getOsVersion();
	}

}
