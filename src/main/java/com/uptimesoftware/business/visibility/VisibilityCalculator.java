package com.uptimesoftware.business.visibility;

import java.util.Set;

import com.google.common.collect.Sets;

public class VisibilityCalculator {
	private final PermissionChecker permissionChecker;
	private final UserGroupFinder userGroupFinder;
	private final TagFinder tagFinder;

	public VisibilityCalculator(PermissionChecker permissionChecker, UserGroupFinder userGroupFinder, TagFinder tagFinder) {
		this.permissionChecker = permissionChecker;
		this.userGroupFinder = userGroupFinder;
		this.tagFinder = tagFinder;
	}

	public Set<Long> getElementIds(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException("Cannot get element ids for null user id.");
		}
		Set<Long> visibleElementIds = Sets.newHashSet();
		visibleElementIds.addAll(getElementIdsFromUserGroups(userId));
		visibleElementIds.addAll(getElementIdsFromVisibleTags(userId));
		return visibleElementIds;
	}

	private Set<Long> getElementIdsFromUserGroups(Long userId) {
		Set<Long> userGroupIds = userGroupFinder.findUserGroupIds(userId);
		return userGroupFinder.findElementIds(userGroupIds);
	}

	private Set<Long> getElementIdsFromVisibleTags(Long userId) {
		Set<Long> visibleTagIds;
		if (permissionChecker.hasAdministratorPermission(userId)) {
			visibleTagIds = tagFinder.findAllTagIds();
		} else {
			DescendantTagsByVisibleTagsCalculator calc = new DescendantTagsByVisibleTagsCalculator(
					tagFinder.findAllTagIdTreeNodes());
			Set<Long> directlyVisibleTagIds = tagFinder.findTagIds(userId);
			visibleTagIds = Sets.newHashSet(calc.getDescendantTagIds(directlyVisibleTagIds));
			visibleTagIds.addAll(directlyVisibleTagIds);
		}
		return tagFinder.findElementIds(visibleTagIds);
	}
}
