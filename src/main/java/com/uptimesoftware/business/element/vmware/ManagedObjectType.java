package com.uptimesoftware.business.element.vmware;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.vmware.vim25.ManagedObjectReference;

public enum ManagedObjectType {
	Folder,
	Datacenter,
	ClusterComputeResource,
	ComputeResource,
	HostSystem,
	ResourcePool,
	VirtualMachine,
	VirtualApp,
	VirtualCenter,
	Datastore,
	PerformanceManager;

	public static final Set<ManagedObjectType> VmwareGroupTypes = Collections.unmodifiableSet(EnumSet.of(Folder, Datacenter,
			ClusterComputeResource, ComputeResource, ResourcePool, VirtualApp));
	public static final Set<ManagedObjectType> VmwareLeafTypes = Collections.unmodifiableSet(EnumSet.of(VirtualMachine,
			HostSystem));

	public static ManagedObjectType getType(ManagedObjectReference ref) {
		for (ManagedObjectType type : ManagedObjectType.values()) {
			if (type.name().equals(ref.getType())) {
				return type;
			}
		}
		return null;
	}

	public boolean isLeaf() {
		return VmwareLeafTypes.contains(this);
	}

	public boolean isGroup() {
		return VmwareGroupTypes.contains(this);
	}

	public boolean isResourcePoolOrVirtualApp() {
		return this.equals(ResourcePool) || this.equals(VirtualApp);
	}
}
