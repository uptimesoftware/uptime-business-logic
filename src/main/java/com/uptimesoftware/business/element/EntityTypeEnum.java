package com.uptimesoftware.business.element;

public enum EntityTypeEnum {
	System(1),
	Node(2),
	Application(3),
	ServiceLevelAgreement(4),
	VmwareObject(6),
	JavaApp(7),
	JavaAppInstance(8);

	public final int id;

	private EntityTypeEnum(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public static EntityTypeEnum getType(long typeId) {
		for (EntityTypeEnum systemType : EntityTypeEnum.values()) {
			if (systemType.id == typeId) {
				return systemType;
			}
		}
		return null;
	}
}