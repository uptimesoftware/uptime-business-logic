package com.uptimesoftware.business.element;

import java.util.Arrays;
import java.util.List;


public class SystemSubTypes {

	private final static List<SystemSubType> vmwareGroupSubTypes;

	static {
		vmwareGroupSubTypes = Arrays.asList(SystemSubType.VmwareFolder, SystemSubType.VmwareDatacenter, SystemSubType.Cluster,
				SystemSubType.ComputeResource, SystemSubType.ResourcePool, SystemSubType.VirtualApp);
	}

	public static boolean isVmwareGroup(SystemSubType subType) {
		return vmwareGroupSubTypes.contains(subType);
	}

	public static List<SystemSubType> getVmwareGroupSystemSubTypes() {
		return vmwareGroupSubTypes;
	}
}
