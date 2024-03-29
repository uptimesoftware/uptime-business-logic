package com.uptimesoftware.business.element.add;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uptimesoftware.business.element.ElementBodyErrorCodes;
import com.uptimesoftware.business.element.ElementTypeEnum;
import com.uptimesoftware.business.elementgroup.ElementGroups;
import com.uptimesoftware.business.validation.oval.ContainsNoWhitespaceCheck;
import com.uptimesoftware.business.validation.oval.ValidateNestedProperty;
import net.sf.oval.constraint.*;

public class AddElementBasicInfo {

	@NotNull(message = "The element name is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@NotBlank(message = "The element name must not be empty", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@Length(max = 50, message = "The element name must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045)
	private final String name;
	@Length(max = 255, message = "The element description must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045)
	private final String description;
	@NotNull(message = "The element hostname is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@NotBlank(message = "The element hostname must not be empty", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@Length(max = 255, message = "The element hostname must not be more than {max} characters in length", errorCode = ElementBodyErrorCodes.TOO_LONG_1045)
	@CheckWith(value = ContainsNoWhitespaceCheck.class, message = "The element hostname must not contain any whitespace", errorCode = ElementBodyErrorCodes.SPACES_IN_HOSTNAME_1040)
	private final String hostname;
	@NotNull(message = "The element type is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	private final ElementTypeEnum type;
	@NotNull(message = "The group id is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@Min(value = ElementGroups.MY_INFRASTRUCTURE_ID_DOUBLE, message = "The group id must be greater than or equal to {min}", errorCode = ElementBodyErrorCodes.NUMBER_OUT_OF_RANGE_1044)
	private final Long groupId;
	@NotNull(message = "The element collection method is required", errorCode = ElementBodyErrorCodes.MISSING_FIELD_1043)
	@ValidateNestedProperty
	private final ElementCollectionMethod collectionMethod;

	@JsonCreator
	public AddElementBasicInfo(@JsonProperty("name") String name, @JsonProperty("description") String description,
			@JsonProperty("hostname") String hostname, @JsonProperty("type") ElementTypeEnum elementType,
			@JsonProperty("groupId") Long groupId, @JsonProperty("collectionMethod") ElementCollectionMethod collectionMethod) {
		this.name = name;
		this.description = description;
		this.hostname = hostname;
		this.type = elementType;
		this.groupId = groupId;
		this.collectionMethod = collectionMethod;
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

	public ElementTypeEnum getType() {
		return type;
	}

	public Long getGroupId() {
		return groupId;
	}

	public ElementCollectionMethod getCollectionMethod() {
		return collectionMethod;
	}

}
