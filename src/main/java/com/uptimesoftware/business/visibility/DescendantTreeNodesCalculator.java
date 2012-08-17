package com.uptimesoftware.business.visibility;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

class DescendantTreeNodesCalculator {

	private final Multimap<Long, Long> parentToChildMap = HashMultimap.create();

	public DescendantTreeNodesCalculator(Set<TreeNodeWithParent> tree) {
		for (TreeNodeWithParent treeNode : tree) {
			parentToChildMap.put(treeNode.getParentId(), treeNode.getId());
		}
	}

	public Set<Long> getDescendantNodeIds(Iterable<Long> treeNodeIds) {
		Set<Long> decendants = Sets.newHashSet();
		for (Long treeNodeId : treeNodeIds) {
			collectDescendantsOf(treeNodeId, decendants);
		}
		return ImmutableSet.copyOf(decendants);
	}

	private void collectDescendantsOf(Long parent, Set<Long> result) {
		Collection<Long> children = parentToChildMap.get(parent);
		result.addAll(children);
		for (Long child : children) {
			collectDescendantsOf(child, result);
		}
	}
}
