package com.uptimesoftware.business.element.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.elementgroup.ElementGroups;
import com.uptimesoftware.business.validation.oval.ContainsNoWhitespaceCheck;
import net.sf.oval.constraint.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateElementInfo {

	@NotNull(message = "The id is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	private final Long id;
	@NotBlank(message = "The element name must be not be empty", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@Length(max = 50, message = "The element name must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045)
	private final String name;
	@Length(max = 255, message = "The element description must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045)
	private final String description;
	@NotBlank(message = "The element hostname must be not be empty", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@Length(max = 255, message = "The element hostname must be not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045)
	@CheckWith(value = ContainsNoWhitespaceCheck.class, message = "The element hostname must not contain any whitespace", errorCode = ElementBodyErrorCodes.SPACES_IN_HOSTNAME_1040)
	private final String hostname;
	private final Boolean isMonitored;
	@Min(value = ElementGroups.MY_INFRASTRUCTURE_ID_DOUBLE, message = "The group id must be greater than or equal to {min}", errorCode = ElementBodyErrorCodes.NUMBER_OUT_OF_RANGE_1044)
	private final Long groupId;

	// TODO: The following are scheduled for a future ticket.

	// private final Boolean inMaintenance;

	// private final String topologicalChildren;

	// private final String topologicalParents;

	// private final String tags;

	@JsonCreator
	public UpdateElementInfo(@JsonProperty("id") Long id, @JsonProperty("name") String name,
			@JsonProperty("description") String description, @JsonProperty("hostname") String hostname,
			@JsonProperty("isMonitored") Boolean isMonitored, @JsonProperty("groupId") Long groupId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.hostname = hostname;
		this.isMonitored = isMonitored;
		this.groupId = groupId;
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

	public Boolean getIsMonitored() {
		return isMonitored;
	}

	public Long getGroupId() {
		return groupId;
	}
}
