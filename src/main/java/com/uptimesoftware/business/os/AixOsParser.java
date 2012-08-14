package com.uptimesoftware.business.os;

class AixOsParser implements OsParser {

	@Override
	public OsInfo parse(String arch, String osver) {
		return new OsInfo("AIX", osver, Architecture.Power);
	}

}
