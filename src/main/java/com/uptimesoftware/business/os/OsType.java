package com.uptimesoftware.business.os;

import com.google.common.base.Strings;

public enum OsType {

	Windows("Windows") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return contains(architecture, "Windows");
		}

		@Override
		boolean checkAgentOrWmi(String firstWord, String osver) {
			if (firstWord.contains("windows") || (isValid(osver) && osver.toLowerCase().contains("windows"))) {
				return true;
			}
			return false;
		}
	},
	Linux("Linux") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return architecture != null
					&& (architecture.contains("Linux") || architecture.contains("Asianux") || architecture.contains("Turbolinux")
							|| architecture.contains("CentOS") || architecture.contains("Open Enterprise Server"));
		}

		@Override
		boolean checkAgentOrWmi(String firstWord, String osver) {
			if (firstWord.contains("linux")) {
				return true;
			}
			return false;
		}
	},
	Solaris("Solaris") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return contains(architecture, "Solaris");
		}

		@Override
		boolean checkAgentOrWmi(String firstWord, String osver) {
			if (firstWord.contains("sunos")) {
				return true;
			}
			return false;
		}
	},
	Novell("Netware") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return contains(architecture, "NetWare");
		}

		@Override
		boolean checkAgentOrWmi(String architecture, String osver) {
			return false;
		}
	},
	Tru64("Tru64") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return false;
		}

		@Override
		boolean checkAgentOrWmi(String firstWord, String osver) {
			if (firstWord.contains("osf")) {
				return true;
			}
			return false;
		}
	},
	AIX("AIX") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return false;
		}

		@Override
		boolean checkAgentOrWmi(String firstWord, String osver) {
			if (firstWord.contains("aix")) {
				return true;
			}
			return false;
		}
	},
	HPUX("HP-UX") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return false;
		}

		@Override
		boolean checkAgentOrWmi(String firstWord, String osver) {
			if (firstWord.contains("hp-ux")) {
				return true;
			}
			return false;
		}
	},
	Unknown("Unknown") {
		@Override
		public boolean matchesVirtualMachineGuest(String architecture) {
			return true;
		}

		@Override
		boolean checkAgentOrWmi(String architecture, String osver) {
			return true;
		}
	};

	private String name;

	private OsType(String name) {
		this.name = name;
	}

	public abstract boolean matchesVirtualMachineGuest(String architecture);

	abstract boolean checkAgentOrWmi(String firstWordOfArch, String osver);

	public boolean matchesAgentOrWmi(String arch, String osver) {
		if (!isValid(arch)) {
			return false;
		}
		String firstWord = getFirstWord(arch);
		if (checkAgentOrWmi(firstWord, osver)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	public static OsType detectVirtualMachineGuest(String architecture) {
		if (!isValid(architecture)) {
			return Unknown;
		}
		for (OsType osType : values()) {
			if (osType.matchesVirtualMachineGuest(architecture)) {
				return osType;
			}
		}
		return Unknown;
	}

	public static OsType detectAgentOrWmi(String arch, String osver) {
		if (!isValid(arch)) {
			return Unknown;
		}
		for (OsType osType : values()) {
			String firstWord = getFirstWord(arch);
			if (osType.checkAgentOrWmi(firstWord, osver)) {
				return osType;
			}
		}
		return Unknown;
	}

	private static boolean contains(String str, String searchStr) {
		if (searchStr == null) {
			return false;
		}
		return Strings.nullToEmpty(str).indexOf(searchStr) > -1;
	}

	private static String getFirstWord(String arch) {
		String systemType = arch;
		int firstSpace = systemType.indexOf(' ');
		if (firstSpace != -1) {
			systemType = arch.substring(0, firstSpace);
		}
		return removeUnderscores(systemType).toLowerCase();
	}

	private static String removeUnderscores(String string) {
		if (string != null) {
			return string.replace("_", " ");
		}
		return "";
	}

	private static boolean isValid(String field) {
		return field != null && !field.isEmpty();
	}
}
