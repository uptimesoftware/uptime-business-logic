package com.uptimesoftware.business.users;

import java.util.Set;

public class VisibilityCalculator {
	private final UserGroupEntityVisibility userGroupEntityVisibility;

	public VisibilityCalculator(UserGroupEntityVisibility userGroupEntityVisibility) {
		this.userGroupEntityVisibility = userGroupEntityVisibility;
	}

	public Set<Long> getEntityIds(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException("Cannot get entity ids for null user id.");
		}
		return userGroupEntityVisibility.getEntityIds(userId);
	}
}
