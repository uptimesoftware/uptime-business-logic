package com.uptimesoftware.business.users;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum PermissionedObjectEnum {
	USERS("user", EnumSet.noneOf(Permission.class)),
	ELEMENTS("entity", EnumSet.of(Permission.VIEW)),
	MONITORS("service", EnumSet.of(Permission.VIEW)),
	ELEMENT_GROUPS("entityGroup", EnumSet.of(Permission.VIEW)),
	TAGS("view", EnumSet.of(Permission.VIEW)),
	ACTION_PROFILES("actionProfile", EnumSet.of(Permission.VIEW)),
	ALERT_PROFILES("alertProfile", EnumSet.of(Permission.VIEW)),
	TIME_PERIODS("timePeriod", EnumSet.of(Permission.VIEW)),
	SLAS("sla", EnumSet.of(Permission.VIEW)),
	SLOS("slo", EnumSet.of(Permission.VIEW));

	public enum Permission {
		VIEW,
		ADD,
		EDIT,
		DELETE;
	}

	private PermissionedObjectEnum(String dbName, Set<Permission> alwaysAllowedPermissions) {
		this.dbName = dbName;
		this.alwaysAllowedPermissions = alwaysAllowedPermissions;
	}

	private static final Map<String, PermissionedObjectEnum> DB_NAMES_MAP;

	static {
		Builder<String, PermissionedObjectEnum> builder = ImmutableMap.builder();
		for (PermissionedObjectEnum value : PermissionedObjectEnum.values()) {
			builder.put(value.getDbName(), value);
		}
		DB_NAMES_MAP = builder.build();
	}

	private String dbName;
	private Set<Permission> alwaysAllowedPermissions;

	public String getDbName() {
		return dbName;
	}

	public Set<Permission> getAlwaysAllowedPermissions() {
		return alwaysAllowedPermissions;
	}

	public static PermissionedObjectEnum fromDbName(String name) {
		return DB_NAMES_MAP.get(name);
	}

}
