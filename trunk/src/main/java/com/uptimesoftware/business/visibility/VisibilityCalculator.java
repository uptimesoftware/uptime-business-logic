package com.uptimesoftware.business.visibility;

import java.util.Set;

import com.google.common.collect.Sets;

public class VisibilityCalculator {
	private final PermissionChecker permissionChecker;
	private final UserGroupFinder userGroupFinder;
	private final TagFinder tagFinder;
	private final ElementGroupFinder elementGroupFinder;

	public VisibilityCalculator(PermissionChecker permissionChecker, UserGroupFinder userGroupFinder,
			ElementGroupFinder elementGroupFinder, TagFinder tagFinder) {
		this.permissionChecker = permissionChecker;
		this.userGroupFinder = userGroupFinder;
		this.elementGroupFinder = elementGroupFinder;
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

	public Set<Long> getElementGroupIds(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException("Cannot get element group ids for null user id.");
		}
		Set<Long> visibleElementGroupIds = Sets.newHashSet();

		Set<Long> visibleElementGroupIdsByUserGroups = getVisibleElementGroupIdsByUser(userId);
		visibleElementGroupIds.addAll(visibleElementGroupIdsByUserGroups);
		visibleElementGroupIds.addAll(getDescendantElementGroupIds(visibleElementGroupIdsByUserGroups));

		return visibleElementGroupIds;
	}

	private Set<Long> getVisibleElementGroupIdsByUser(Long userId) {
		Set<Long> userGroupIds = userGroupFinder.findUserGroupsByUser(userId);
		return elementGroupFinder.findElementGroupsByUserGroups(userGroupIds);
	}

	private Set<Long> getDescendantElementGroupIds(Set<Long> visibleElementGroupIdsByUserGroups) {
		DescendantTreeNodesCalculator calc = new DescendantTreeNodesCalculator(
				elementGroupFinder.findAllElementGroupIdTreeNodes());
		return calc.getDescendantNodeIds(visibleElementGroupIdsByUserGroups);
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

		DescendantTreeNodesCalculator calc = new DescendantTreeNodesCalculator(tagFinder.findAllTagIdTreeNodes());

		Set<Long> directlyVisibleTagIds = Sets.newHashSet();
		directlyVisibleTagIds.addAll(tagFinder.findTagsByUserGroups(userGroupIds));

		Set<Long> visibleTagIds = Sets.newHashSet();
		visibleTagIds.addAll(calc.getDescendantNodeIds(directlyVisibleTagIds));
		visibleTagIds.addAll(directlyVisibleTagIds);
		return visibleTagIds;
	}
}
