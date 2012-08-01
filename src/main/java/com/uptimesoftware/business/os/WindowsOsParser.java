package com.uptimesoftware.business.os;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

class WindowsOsParser implements OsParser {

	private static final Map<String, OsInfo> windowsNameMap;
	private static final OsInfo DEFAULT = new OsInfo("Windows", "");

	static {
		windowsNameMap = Maps.newHashMap();
		windowsNameMap.put("528", new OsInfo("Windows", "NT 3.1"));
		windowsNameMap.put("807", new OsInfo("Windows", "NT 3.5"));
		windowsNameMap.put("1057", new OsInfo("Windows", "NT 3.51"));
		windowsNameMap.put("1381", new OsInfo("Windows", "NT 4.0"));
		windowsNameMap.put("2195", new OsInfo("Windows", "2000"));
		windowsNameMap.put("2600", new OsInfo("Windows", "XP"));
		windowsNameMap.put("3790", new OsInfo("Windows", "Server 2003"));
		windowsNameMap.put("6000", new OsInfo("Windows", "Vista"));
		windowsNameMap.put("6001", new OsInfo("Windows", "Vista/Server 2008"));
		windowsNameMap.put("6002", new OsInfo("Windows", "Vista/Server 2008"));
		windowsNameMap.put("7600", new OsInfo("Windows", "7/Server 2008 R2"));
		windowsNameMap.put("7601", new OsInfo("Windows", "7/Server 2008 R2"));
	}

	@Override
	public OsInfo parse(String arch, String osver) {
		OsInfo info = windowsNameMap.get(osver);
		if (info == null) {
			info = tryWmi(osver);
		}
		Architecture architecture = Architecture.getArchitecture(arch);
		return new OsInfo(info.getOsType(), info.getOsVersion(), architecture);
	}

	private OsInfo tryWmi(String osver) {
		if (osver != null && osver.contains("Windows")) {
			String windowsVersion = osver.replaceAll("^.*Windows\\s+", "").trim();
			return new OsInfo("Windows", windowsVersion);
		}
		return DEFAULT;
	}

	public static Set<String> recognizedVersionNumbers() {
		return windowsNameMap.keySet();
	}

}
