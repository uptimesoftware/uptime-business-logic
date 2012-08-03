package com.uptimesoftware.business.visibility;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

class DescendantTagsByVisibleTagsCalculator {

	private final Multimap<Long, Long> parentToChildMap = HashMultimap.create();

	public DescendantTagsByVisibleTagsCalculator(Set<TagIdTreeNode> tagIdTree) {
		for (TagIdTreeNode tagIdTreeNode : tagIdTree) {
			parentToChildMap.put(tagIdTreeNode.getParentId(), tagIdTreeNode.getId());
		}
	}

	public Set<Long> getDescendantTagIds(Iterable<Long> tagIds) {
		Set<Long> decendants = Sets.newHashSet();
		for (Long tagId : tagIds) {
			collectDescendantsOf(tagId, decendants);
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
