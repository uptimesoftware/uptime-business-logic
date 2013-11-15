package com.uptimesoftware.business.element.update;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.validation.oval.ContainsNoSpacesCheck;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateElementInfo {

	@NotNull(message = "The id is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD)
	private final Long id;
	@NotBlank(message = "The element name must be not be empty", errorCode = ElementBodyErrorCodes.MISSING_FIELD)
	@Length(max = 50, message = "The element name must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG)
	private final String name;
	@Length(max = 255, message = "The element description must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG)
	private final String description;
	@NotBlank(message = "The element hostname must be not be empty", errorCode = ElementBodyErrorCodes.MISSING_FIELD)
	@Length(max = 255, message = "The element hostname must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG)
	@CheckWith(value = ContainsNoSpacesCheck.class, message = "The element hostname must not contain any spaces", errorCode = ElementBodyErrorCodes.SPACES_IN_HOSTNAME)
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
