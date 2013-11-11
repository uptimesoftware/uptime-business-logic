package com.uptimesoftware.business.element.update;

import net.sf.oval.constraint.Length;

import com.uptimesoftware.business.element.ElementBodyErrorCodes;

public class UpdateElementInfo {

	private final Long id;
	@Length(max = 50, message = "The element name must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG)
	private final String name;
	@Length(max = 255, message = "The element description must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG)
	private final String description;
	@Length(max = 255, message = "The element hostname must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG)
	private final String hostname;

	// TODO: The following are scheduled for a future ticket.
	// private final Boolean isMonitored;

	// private final Boolean inMaintenance;

	// private final String topologicalChildren;

	// private final String topologicalParents;

	// private final String tags;

	/**
	 * for json deserialization
	 */
	public UpdateElementInfo() {
		this(null, null, null, null);
	}

	public UpdateElementInfo(Long id, String name, String description, String hostname) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.hostname = hostname;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getHostname() {
		return hostname;
	}

}
