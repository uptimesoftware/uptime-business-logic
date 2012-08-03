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
		Set<Long> userGroupIds = userGroupFinder.findUserGroupsByUser(userId);
		visibleElementIds.addAll(userGroupFinder.findElementsByUserGroups(userGroupIds));
		visibleElementIds.addAll(getElementIdsFromVisibleTags(userId, userGroupIds));
		return visibleElementIds;
	}

	private Set<Long> getElementIdsFromVisibleTags(Long userId, Set<Long> userGroupIds) {
		Set<Long> visibleTagIds;
		if (permissionChecker.hasAdministratorPermission(userId)) {
			visibleTagIds = tagFinder.findAllTags();
		} else {
			DescendantTagsByVisibleTagsCalculator calc = new DescendantTagsByVisibleTagsCalculator(
					tagFinder.findAllTagIdTreeNodes());

			Set<Long> directlyVisibleTagIds = Sets.newHashSet();
			directlyVisibleTagIds.addAll(tagFinder.findTagsByUser(userId));
			directlyVisibleTagIds.addAll(tagFinder.findTagsByUserGroups(userGroupIds));
			
			visibleTagIds = Sets.newHashSet();
			visibleTagIds.addAll(calc.getDescendantTagIds(directlyVisibleTagIds));
			visibleTagIds.addAll(directlyVisibleTagIds);
		}
		return tagFinder.findElementsByTags(visibleTagIds);
	}
}
