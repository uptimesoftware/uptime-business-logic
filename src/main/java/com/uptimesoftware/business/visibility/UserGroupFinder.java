package com.uptimesoftware.business.visibility;

import java.util.Set;

public interface UserGroupFinder {

	/**
	 * Find all the user group ids that a user belongs to.
	 */
	Set<Long> findUserGroupsByUser(Long userId);

	/**
	 * Find all the element ids that the specified user groups can see.
	 */
	Set<Long> findElementsByUserGroups(Set<Long> userGroupIds);
}
