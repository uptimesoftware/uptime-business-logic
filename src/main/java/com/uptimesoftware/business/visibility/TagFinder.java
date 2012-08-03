package com.uptimesoftware.business.visibility;

import java.util.Set;

public interface TagFinder {

	Set<TagIdTreeNode> findAllTagIdTreeNodes();
	
	Set<Long> findAllTagIds();

	Set<Long> findTagIds(Long userId);

	Set<Long> findElementIds(Set<Long> tagIds);
}
