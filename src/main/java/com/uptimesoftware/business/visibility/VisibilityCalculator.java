package com.uptimesoftware.business.visibility;

import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class VisibilityCalculator {
	private final UserGroupElementVisibilityData userGroupElementVisibility;
	private final TagIdData tagIdsFinder;

	public VisibilityCalculator(UserGroupElementVisibilityData userGroupElementVisibility, TagIdData tagIdsFinder) {
		this.userGroupElementVisibility = userGroupElementVisibility;
		this.tagIdsFinder = tagIdsFinder;
	}

	public Set<Long> getElementIds(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException("Cannot get element ids for null user id.");
		}
		Set<Long> visibleElementIds = Sets.newHashSet();
		visibleElementIds.addAll(userGroupElementVisibility.getElementIds(userId));
		visibleElementIds.addAll(getElementIdsFromVisibleTags(userId));
		return visibleElementIds;
	}

	private Set<Long> getElementIdsFromVisibleTags(Long userId) {
		DescendantTagsByVisibleTagsCalculator calc = new DescendantTagsByVisibleTagsCalculator(
				tagIdsFinder.findAllTagIdTreeNodes());
		Set<Long> directlyVisibleViewIds = tagIdsFinder.findVisibleTagIds(userId);
		Set<Long> visibleViewIds = Sets.newHashSet(calc.getDescendantTagIds(directlyVisibleViewIds));
		visibleViewIds.addAll(directlyVisibleViewIds);

		Set<Long> elementIds = Sets.newHashSet();
		Multimap<Long, Long> allElementIdsByViewIds = tagIdsFinder.findAllElementIdsByTagIds();
		for (Long viewId : visibleViewIds) {
			elementIds.addAll(allElementIdsByViewIds.get(viewId));
		}
		return elementIds;
	}
}
