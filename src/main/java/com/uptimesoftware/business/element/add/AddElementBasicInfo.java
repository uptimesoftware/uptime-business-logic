package com.uptimesoftware.business.element.add;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import com.uptimesoftware.business.element.ElementTypeEnum;
import com.uptimesoftware.business.validation.oval.ValidateNestedProperty;

public class AddElementBasicInfo {

	@NotNull(message = "The element name is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	@Length(max = 50, message = "The element name must be not be more than {max} characters in length", errorCode = AddElementErrorCodes.TOO_LONG)
	private final String name;
	@Length(max = 255, message = "The element description must be not be more than {max} characters in length", errorCode = AddElementErrorCodes.TOO_LONG)
	private final String description;
	@NotNull(message = "The element hostname is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	@Length(max = 255, message = "The element hostname must be not be more than {max} characters in length", errorCode = AddElementErrorCodes.TOO_LONG)
	private final String hostname;
	@NotNull(message = "The element type is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	private final ElementTypeEnum type;
	@NotNull(message = "The element collection method is required", errorCode = AddElementErrorCodes.MISSING_FIELD)
	@ValidateNestedProperty
	private final ElementCollectionMethod collectionMethod;

	/**
	 * for json deserialization
	 */
	public AddElementBasicInfo() {
		this(null, null, null, null, null);
	}

	public AddElementBasicInfo(String name, String description, String hostname, ElementTypeEnum elementType,
			ElementCollectionMethod collectionMethod) {
		this.name = name;
		this.description = description;
		this.hostname = hostname;
		this.type = elementType;
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

	public ElementCollectionMethod getCollectionMethod() {
		return collectionMethod;
	}

}
