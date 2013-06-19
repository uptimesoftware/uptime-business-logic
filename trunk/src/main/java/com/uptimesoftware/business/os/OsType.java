package com.uptimesoftware.business.os;

import com.google.common.base.Strings;
import com.uptimesoftware.business.os.OsVersions.UptimeDefinedOsVersion;

public enum OsType {

	Windows("Windows") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return contains(architecture, "Windows");
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return firstWordContains(arch, "windows") || Strings.nullToEmpty(osver).toLowerCase().contains("windows");
		}
	},
	Linux("Linux") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return architecture != null
					&& (architecture.contains("Linux") || architecture.contains("Asianux") || architecture.contains("Turbolinux")
							|| architecture.contains("CentOS") || architecture.contains("Open Enterprise Server"));
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return firstWordContains(arch, "linux");
		}
	},
	Solaris("Solaris") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return contains(architecture, "Solaris");
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return firstWordContains(arch, "sunos");
		}
	},
	Novell("Netware") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return contains(architecture, "NetWare");
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return false;
		}
	},
	Tru64("Tru64") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return false;
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return firstWordContains(arch, "osf");
		}
	},
	AIX("AIX") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return false;
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return firstWordContains(arch, "aix");
		}
	},
	HPUX("HP-UX") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return false;
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return firstWordContains(arch, "hp-ux");
		}
	},
	Unknown("Unknown") {
		@Override
		public boolean matchesVirtualMachineArchitecture(String architecture) {
			return true;
		}

		@Override
		boolean matchesArchAndOsver(String arch, String osver) {
			return true;
		}
	};

	private String name;

	private OsType(String name) {
		this.name = name;
	}

	public abstract boolean matchesVirtualMachineArchitecture(String architecture);

	abstract boolean matchesArchAndOsver(String arch, String osver);

	@Override
	public String toString() {
		return name;
	}

	public static OsType fromArchAndOsver(String arch, String osver) {
		if (OsVersions.getUptimeDefined(osver) == UptimeDefinedOsVersion.VirtualMachine) {
			return fromVirtualMachineArchitecture(arch);
		}
		if (Strings.isNullOrEmpty(arch)) {
			return Unknown;
		}
		for (OsType osType : values()) {
			if (osType.matchesArchAndOsver(arch, osver)) {
				return osType;
			}
		}
		return Unknown;
	}

	static OsType fromVirtualMachineArchitecture(String architecture) {
		if (Strings.isNullOrEmpty(architecture)) {
			return Unknown;
		}
		for (OsType osType : values()) {
			if (osType.matchesVirtualMachineArchitecture(architecture)) {
				return osType;
			}
		}
		return Unknown;
	}

	private static boolean contains(String str, String searchStr) {
		if (Strings.isNullOrEmpty(str)) {
			return false;
		}
		return str.indexOf(searchStr) > -1;
	}

	private static boolean firstWordContains(String str, String searchStr) {
		if (Strings.isNullOrEmpty(str)) {
			return false;
		}
		String firstWord;
		int firstSpace = str.indexOf(' ');
		if (firstSpace == -1) {
			firstWord = str;
		} else {
			firstWord = str.substring(0, firstSpace);
		}
		return contains(firstWord.replace('_', ' ').toLowerCase(), searchStr);
	}
}
