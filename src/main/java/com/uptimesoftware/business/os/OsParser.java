package com.uptimesoftware.business.os;

interface OsParser {
	OsInfo parse(String arch, String osver);
}
