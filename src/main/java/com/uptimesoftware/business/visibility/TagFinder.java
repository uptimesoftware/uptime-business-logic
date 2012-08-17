package com.uptimesoftware.business.visibility;

import java.util.Set;

public interface TagFinder {

	/**
	 * Find all the tag ids in the database, each with their linked parent id.
	 */
	Set<TreeNodeWithParent> findAllTagIdTreeNodes();

	/**
	 * Find all the tag ids in the database.
	 */
	Set<Long> findAllTags();

	/**
	 * Find all the tag ids that a user can see directly.
	 */
	Set<Long> findTagsByUser(Long userId);

	/**
	 * Find all the tag ids that the specified user groups can see.
	 */
	Set<Long> findTagsByUserGroups(Set<Long> userGroupIds);

	/**
	 * Find all the element ids that are tagged with the specified ids.
	 */
	Set<Long> findElementsByTags(Set<Long> tagIds);
}
