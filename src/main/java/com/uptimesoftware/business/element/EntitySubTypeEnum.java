package com.uptimesoftware.business.element;


public enum EntitySubTypeEnum {
	Agent(1),
	NetworkDevice(2),
	NovellNrm(4),
	VirtualNode(5),
	SnmpV2(6),
	SnmpV3(7),
	VmwareEsx(9),
	LparVio(10),
	LparHmc(11),
	WmiAgentless(12),
	VirtualCenter(13),
	VmwareFolder(14),
	VmwareDatacenter(15),
	Cluster(16),
	ComputeResource(17),
	ResourcePool(18),
	VirtualApp(19),
	HostSystem(20),
	VirtualMachine(21);

	public final long id;

	private EntitySubTypeEnum(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	public static EntitySubTypeEnum getType(long typeId) {
		for (EntitySubTypeEnum subType : EntitySubTypeEnum.values()) {
			if (subType.id == typeId) {
				return subType;
			}
		}
		return null;
	}

	public boolean isVmwareGroup() {
		return EntitySubTypeEnums.isVmwareGroup(this);
	}
}