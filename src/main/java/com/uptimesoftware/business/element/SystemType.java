package com.uptimesoftware.business.element;

public enum SystemType {
	System(1), Node(2), Application(3), ServiceLevelAgreement(4), DataCenter(5), VmwareObject(6);

	public final int id;

	private SystemType(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public static SystemType getType(long typeId) {
		for (SystemType systemType : SystemType.values()) {
			if (systemType.id == typeId) {
				return systemType;
			}
		}
		return null;
	}
}