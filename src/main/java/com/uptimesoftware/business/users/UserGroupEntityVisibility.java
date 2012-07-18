package com.uptimesoftware.business.users;

import java.util.Set;

public interface UserGroupEntityVisibility {
	public Set<Long> getEntityIds(Long userId);
}
