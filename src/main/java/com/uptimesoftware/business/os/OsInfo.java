package com.uptimesoftware.business.os;

import java.util.Objects;

import com.google.common.base.Strings;

public class OsInfo {
	private final String osType;
	private final String osVersion;
	private final Architecture arch;

	OsInfo(String osType, String osVersion) {
		this(osType, osVersion, Architecture.Unknown);
	}

	OsInfo(String osType, String osVersion, Architecture arch) {
		this.osType = osType;
		this.osVersion = Strings.nullToEmpty(osVersion);
		this.arch = arch;
	}

	public String getOsType() {
		return osType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public Architecture getArch() {
		return arch;
	}

	public String getArchName() {
		return arch.toString();
	}

	public String getOsFull() {
		if (Strings.isNullOrEmpty(osVersion)) {
			return osType;
		}
		return osType + " " + osVersion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(osType, osVersion, arch);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OsInfo other = (OsInfo) obj;
		return Objects.equals(osType, other.osType) && Objects.equals(osVersion, other.osVersion)
				&& Objects.equals(arch, other.arch);
	}

	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this).add("osType", osType).add("osVersion", osVersion)
				.add("arch", arch).toString();
	}

}
