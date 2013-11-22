package com.uptimesoftware.business.error;

import javax.servlet.http.HttpServletResponse;

public enum UptimeErrorEnum {
	NotFound("UT-0404", HttpServletResponse.SC_NOT_FOUND, "A resource was not found."),
	Unknown("UT-0500", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unknown error has occurred."),
	UnknownException(
			"UT-0555",
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			"An exception has occurred: {0}. Check uptime_controller.log for details."),
	BadRequest(
			"UT-0400",
			HttpServletResponse.SC_BAD_REQUEST,
			"Bad request: {0} caused an exception (stack trace in uptime_controller.log @ {1})."),
	MethodNotAllowed(
			"UT-0405",
			HttpServletResponse.SC_METHOD_NOT_ALLOWED,
			"Method not allowed: {0} caused an exception (stack trace in uptime_controller.log @ {1})."),
	MethodNoPermission("UT-0405", HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed: {0}."),
	MediaTypeNotAcceptable(
			"UT-0406",
			HttpServletResponse.SC_NOT_ACCEPTABLE,
			"Media type not acceptable: {0} caused an exception (stack trace in uptime_controller.log @ {1})."),
	MediaTypeNotSupported(
			"UT-0415",
			HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
			"Media type not supported: {0} caused an exception (stack trace in uptime_controller.log @ {1})."),
	InternalServerError(
			"UT-0560",
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			"Internal Server Error: {0} caused an exception (stack trace in uptime_controller.log @ {1})."),
	ElementDoesNotExist("UT-1000", HttpServletResponse.SC_NOT_FOUND, "The element id ''{0}'' does not exist."),
	MonitorDoesNotExist("UT-1001", HttpServletResponse.SC_NOT_FOUND, "The service monitor id ''{0}'' does not exist."),
	ElementGroupDoesNotExist("UT-1002", HttpServletResponse.SC_NOT_FOUND, "The element group id ''{0}'' does not exist."),
	ElementFilterExpired(
			"UT-1010",
			HttpServletResponse.SC_GONE,
			"The element filter id ''{0}'' has expired. Please create a new filter."),
	MonitorFilterExpired(
			"UT-1011",
			HttpServletResponse.SC_GONE,
			"The service monitor filter id ''{0}'' has expired. Please create a new filter."),
	ElementGroupFilterExpired(
			"UT-1012",
			HttpServletResponse.SC_GONE,
			"The element group filter id ''{0}'' has expired. Please create a new filter."),
	InvalidElementFilter("UT-1013", HttpServletResponse.SC_BAD_REQUEST, "The element filter ''{0}'' is not a valid filter: {1}"),
	InvalidMonitorFilter(
			"UT-1014",
			HttpServletResponse.SC_BAD_REQUEST,
			"The service monitor filter ''{0}'' is not a valid filter: {1}"),
	InvalidElementGroupFilter(
			"UT-1015",
			HttpServletResponse.SC_BAD_REQUEST,
			"The element group filter ''{0}'' is not a valid filter: {1}"),
	MessengerCommunicationError(
			MessengerErrors.MessengerCommunicationErrorCode,
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			"There was a problem communicating with the messenger service: ''{0}''"),
	MessengerJsonInvalid(
			"UT-1017",
			HttpServletResponse.SC_BAD_REQUEST,
			"Invalid JSON was received by the messenger server: ''{0}''"),
	MessengerInvalidRequest(
			"UT-1018",
			HttpServletResponse.SC_BAD_REQUEST,
			"The JSON sent to the messenger service is not a valid Request object: ''{0}''"),
	MessengerMethodNotFound(
			"UT-1019",
			HttpServletResponse.SC_BAD_REQUEST,
			"The method sent to the messenger service does not exist / is not available: ''{0}''"),
	MessengerInvalidParams(
			"UT-1020",
			HttpServletResponse.SC_BAD_REQUEST,
			"The parameters sent to the messenger service are invalid: ''{0}''"),
	MessengerInternalError(
			"UT-1021",
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			"Internal messenger service exception: ''{0}''"),
	MessengerUnknownException(
			"UT-1022",
			HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			"Unknown exception received from the messenger service: ''{0}''"),
	MissingCoreUuidException("UT-1023", HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to obtain Core UUID"),
	NoAddElementPermission("UT-1024", HttpServletResponse.SC_BAD_REQUEST, "User does not have permission to add elements"),
	InvalidRequestBodyJson("UT-1025", HttpServletResponse.SC_BAD_REQUEST, "Could not parse json in http request body: {0}"),
	VmNotDeleted(
			"UT-1026",
			HttpServletResponse.SC_FORBIDDEN,
			"Virtual Machines and ESX Hosts which have been ignored cannot be deleted"),
	ClusterEsxNotDeleted("UT-1027", HttpServletResponse.SC_FORBIDDEN, "ESX Hosts which belong to a cluster cannot be deleted"),
	UrlIdBodyIdMismatch("UT-1028", HttpServletResponse.SC_BAD_REQUEST, "Element Id in URL and body do not match"),
	DuplicateHostname(
			"UT-1029",
			HttpServletResponse.SC_BAD_REQUEST,
			"Another element (which you may not have permission to view) exists with this hostname"),
	DuplicateElementName(
			"UT-1030",
			HttpServletResponse.SC_BAD_REQUEST,
			"Another element (which you may not have permission to view) exists with this name"),
	KnownCoreError("DUMMY-SUPPLIED_BY_CORE", HttpServletResponse.SC_BAD_REQUEST, "{0}");

	private final String code;
	private final int httpStatus;
	private final String description;

	private UptimeErrorEnum(String code, int httpStatus, String description) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getDescription() {
		return description;
	}

}
