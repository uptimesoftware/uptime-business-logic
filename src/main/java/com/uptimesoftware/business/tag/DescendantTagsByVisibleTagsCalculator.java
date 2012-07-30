package com.uptimesoftware.business.tag;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class DescendantTagsByVisibleTagsCalculator {

	private final Multimap<Long, Long> parentToChildMap = HashMultimap.create();

	public DescendantTagsByVisibleTagsCalculator(Set<TagIdTreeNode> tagIdTree) {
		for (TagIdTreeNode tagIdTreeNode : tagIdTree) {
			parentToChildMap.put(tagIdTreeNode.getParentId(), tagIdTreeNode.getId());
		}
	}

	public Set<Long> getDescendantTagIds(Iterable<Long> viewIds) {
		Set<Long> decendants = Sets.newHashSet();
		for (Long viewId : viewIds) {
			collectDescendantsOf(viewId, decendants);
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
