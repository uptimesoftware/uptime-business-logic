package com.uptimesoftware.business.visibility;

public interface PermissionChecker {
	
	boolean hasAdministratorPermission(Long userId);

}
