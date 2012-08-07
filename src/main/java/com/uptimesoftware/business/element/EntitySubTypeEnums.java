package com.uptimesoftware.business.element;

import java.util.Arrays;
import java.util.List;


public class EntitySubTypeEnums {

	private final static List<EntitySubTypeEnum> vmwareGroupSubTypes;

	static {
		vmwareGroupSubTypes = Arrays.asList(EntitySubTypeEnum.VmwareFolder, EntitySubTypeEnum.VmwareDatacenter, EntitySubTypeEnum.Cluster,
				EntitySubTypeEnum.ComputeResource, EntitySubTypeEnum.ResourcePool, EntitySubTypeEnum.VirtualApp);
	}

	public static boolean isVmwareGroup(EntitySubTypeEnum subType) {
		return vmwareGroupSubTypes.contains(subType);
	}

	public static List<EntitySubTypeEnum> getVmwareGroupSystemSubTypes() {
		return vmwareGroupSubTypes;
	}
}
