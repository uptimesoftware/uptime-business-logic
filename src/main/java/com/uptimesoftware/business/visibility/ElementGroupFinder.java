package com.uptimesoftware.business.visibility;

import java.util.Set;

public interface ElementGroupFinder {

	/**
	 * Find all the element groups in the database, each with their linked parent id.
	 */
	Set<TreeNodeWithParent> findAllElementGroupIdTreeNodes();

	/**
	 * Find all the element group ids that the specified user groups can see.
	 */
	Set<Long> findElementGroupsByUserGroups(Set<Long> userGroupIds);
}
