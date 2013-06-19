package com.uptimesoftware.business.os;

class UnrecognizedOsInfo extends OsInfo {

	UnrecognizedOsInfo(String osType, String osVersion, Architecture arch) {
		super(osType, osVersion, arch);
	}

	@Override
	public String getOsFull() {
		return getOsType() + " - " + getOsVersion();
	}

}
