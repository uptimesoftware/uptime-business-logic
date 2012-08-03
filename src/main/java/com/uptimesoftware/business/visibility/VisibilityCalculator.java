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
		Set<Long> tagIds = getVisibleTagIds(userId, userGroupIds);
		visibleElementIds.addAll(tagFinder.findElementsByTags(tagIds));
		return visibleElementIds;
	}

	public Set<Long> getTagIds(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException("Cannot get tag ids for null user id.");
		}
		return getVisibleTagIds(userId, null);
	}

	private Set<Long> getVisibleTagIds(Long userId, Set<Long> userGroupIds) {
		if (permissionChecker.hasAdministratorPermission(userId)) {
			return tagFinder.findAllTags();
		}
		if (userGroupIds == null) {
			userGroupIds = userGroupFinder.findUserGroupsByUser(userId);
		}

		DescendantTagsByVisibleTagsCalculator calc = new DescendantTagsByVisibleTagsCalculator(tagFinder.findAllTagIdTreeNodes());

		Set<Long> directlyVisibleTagIds = Sets.newHashSet();
		directlyVisibleTagIds.addAll(tagFinder.findTagsByUser(userId));
		directlyVisibleTagIds.addAll(tagFinder.findTagsByUserGroups(userGroupIds));

		Set<Long> visibleTagIds = Sets.newHashSet();
		visibleTagIds.addAll(calc.getDescendantTagIds(directlyVisibleTagIds));
		visibleTagIds.addAll(directlyVisibleTagIds);
		return visibleTagIds;
	}
}
