package com.uptimesoftware.business.elementgroup;

public class ElementGroups {
	public final static Long MY_INFRASTRUCTURE_ID = 1L;
	// Needed for oval validation
	public static final double MY_INFRASTRUCTURE_ID_DOUBLE = 1.0;
	public final static String MY_INFRASTRUCTURE_NAME = "My Infrastructure";

	public static boolean isValidId(long groupId) {
		return groupId >= MY_INFRASTRUCTURE_ID;
	}
}
