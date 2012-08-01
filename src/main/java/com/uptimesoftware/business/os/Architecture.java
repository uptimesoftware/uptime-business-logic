package com.uptimesoftware.business.os;

public enum Architecture {
	x86("x86"),
	x86_64("x86_64"),
	IA64("IA64"),
	Sparc("Sparc"),
	Alpha("Alpha"),
	Power("Power"),
	PARISC("PA-RISC"),
	Unknown("Unknown");

	private String name;

	private Architecture(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public static Architecture getArchitecture(String arch) {
		if (arch == null || arch.isEmpty()) {
			return Unknown;
		}

		String systemArch = arch.toLowerCase();
		if (systemArch.contains("x86_64") || systemArch.contains("amd64") || systemArch.contains("em64t")
				|| systemArch.contains("intel64") || systemArch.contains("(64-bit)")) {
			return x86_64;
		}

		if (systemArch.contains("x86") || systemArch.contains("i586") || systemArch.contains("i686")
				|| systemArch.contains("i386") || systemArch.contains("(32-bit)")) {
			return x86;
		}

		if (systemArch.contains("sparc") || systemArch.contains("sun4")) {
			return Sparc;
		}

		if (systemArch.contains("alpha")) {
			return Alpha;
		}

		if (systemArch.contains("powerpc") || systemArch.contains("aix")) {
			return Power;
		}

		if (systemArch.contains("ia64")) {
			return IA64;
		}

		if (systemArch.contains("hp-ux")) {
			return PARISC;
		}

		return Unknown;
	}
}
