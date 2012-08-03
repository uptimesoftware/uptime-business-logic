package com.uptimesoftware.business.visibility;

import java.util.Set;

import com.google.common.collect.Multimap;

public interface TagIdData {

	Set<TagIdTreeNode> findAllTagIdTreeNodes();

	Set<Long> findVisibleTagIds(Long userId);

	Multimap<Long, Long> findAllElementIdsByTagIds();
}
