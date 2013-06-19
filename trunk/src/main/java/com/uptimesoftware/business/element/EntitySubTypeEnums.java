package com.uptimesoftware.business.element;

import java.util.Arrays;
import java.util.List;

public class EntitySubTypeEnums {

	private final static List<EntitySubTypeEnum> vmwareGroupEntitySubTypes;

	static {
		vmwareGroupEntitySubTypes = Arrays.asList(EntitySubTypeEnum.VmwareFolder, EntitySubTypeEnum.VmwareDatacenter,
				EntitySubTypeEnum.Cluster, EntitySubTypeEnum.ComputeResource, EntitySubTypeEnum.ResourcePool,
				EntitySubTypeEnum.VirtualApp);
	}

	public static boolean isVmwareGroup(EntitySubTypeEnum entitySubType) {
		return vmwareGroupEntitySubTypes.contains(entitySubType);
	}

	public static List<EntitySubTypeEnum> getVmwareGroupEntitySubTypes() {
		return vmwareGroupEntitySubTypes;
	}
}
