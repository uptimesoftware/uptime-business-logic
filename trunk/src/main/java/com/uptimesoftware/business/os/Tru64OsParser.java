package com.uptimesoftware.business.os;

class Tru64OsParser implements OsParser {

	@Override
	public OsInfo parse(String arch, String osver) {
		return new OsInfo("Tru64", osver == null ? null : osver.substring(1), Architecture.getArchitecture(arch));
	}

}
