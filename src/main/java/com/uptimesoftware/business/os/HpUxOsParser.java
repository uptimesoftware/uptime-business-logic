package com.uptimesoftware.business.os;

public class HpUxOsParser implements OsParser {

	@Override
	public OsInfo parse(String arch, String osver) {
		return new OsInfo("HP-UX", osver == null ? null : osver.substring(2), Architecture.getArchitecture(arch));
	}

}
