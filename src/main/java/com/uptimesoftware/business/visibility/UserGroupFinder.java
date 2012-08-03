package com.uptimesoftware.business.visibility;

import java.util.Set;

public interface UserGroupFinder {

	Set<Long> findUserGroupIds(Long userId);
	
	Set<Long> findElementIds(Set<Long> userGroupIds);
}
