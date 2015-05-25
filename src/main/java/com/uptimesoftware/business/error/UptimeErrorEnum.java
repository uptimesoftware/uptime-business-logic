package com.uptimesoftware.business.error;

import javax.servlet.http.HttpServletResponse;

import com.uptimesoftware.business.element.ElementBodyErrorCodes;

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
	AuthorizationException(
			"UT-0401",
			HttpServletResponse.SC_UNAUTHORIZED,
			"Failed To login. Reason: {0}"),
	NotAuthorized(
			"UT-0401",
			HttpServletResponse.SC_UNAUTHORIZED,
			"Full authentication is required to access this resource."),
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
	NotSingleElement(
			"UT-1031",
			HttpServletResponse.SC_BAD_REQUEST,
			"The element id ''{0}'' does not correspond to a single element."),
	ManualMonitorNotDeleted("UT-1032", HttpServletResponse.SC_FORBIDDEN, "Manually monitored hosts cannot be deleted"),
	LicenseViolation("UT-1033", HttpServletResponse.SC_BAD_REQUEST, "Adding this system will violate the current license"),
	WmiNotSupported(
			"UT-1034",
			HttpServletResponse.SC_BAD_REQUEST,
			"The up.time Data Collector does not support WMI communication."),
	UnknownAddElementError(
			"UT-1035",
			HttpServletResponse.SC_BAD_REQUEST,
			"An exception occurred attempting to add an element: {0}"),
	UnknownCoreError("UT-1036", HttpServletResponse.SC_BAD_REQUEST, "An exception occurred on the up.time Data Collector: {0}"),
	DuplicateVmwareUuid(
			"UT-1037",
			HttpServletResponse.SC_BAD_REQUEST,
			"This element with UUID {0} already exists within a virtual center in up.time"),
	EmptyHostname("UT-1038", HttpServletResponse.SC_BAD_REQUEST, "Host name missing or blank"),
	EmptyDisplayName("UT-1039", HttpServletResponse.SC_BAD_REQUEST, "Display name missing or blank"),
	SpacesInHostname(
			ElementBodyErrorCodes.SPACES_IN_HOSTNAME_1040,
			HttpServletResponse.SC_BAD_REQUEST,
			"Host name cannot have spaces in it"),
	ProxyError("UT-1041", HttpServletResponse.SC_BAD_REQUEST, "Proxy Error: {0}"),
	HmcViolation("UT-1042", HttpServletResponse.SC_BAD_REQUEST, "HMC Error: {0}"),
	MissingField(ElementBodyErrorCodes.MISSING_FIELD_1043, HttpServletResponse.SC_BAD_REQUEST, "{0}"),
	FieldNumberOutOfRange(ElementBodyErrorCodes.NUMBER_OUT_OF_RANGE_1044, HttpServletResponse.SC_BAD_REQUEST, "{0}"),
	FieldTooLong(ElementBodyErrorCodes.TOO_LONG_1045, HttpServletResponse.SC_BAD_REQUEST, "{0}"),
	InvalidRequestParams(
			"UT-1046",
			HttpServletResponse.SC_BAD_REQUEST,
			"The parameter sent in the request is invalid: ''{0}''"),

	KnownCoreError("DUMMY-SUPPLIED_BY_CORE", HttpServletResponse.SC_BAD_REQUEST, "{0}");

	private final String code;
	private final int httpStatus;
	private final String description;
	private final UptimeError uptimeError;

	private UptimeErrorEnum(String code, int httpStatus, String description) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.description = description;
		this.uptimeError = new ForwardingUptimeError(this);
	}

	private static class ForwardingUptimeError implements UptimeError {

		private final UptimeErrorEnum uptimeErrorEnum;

		public ForwardingUptimeError(UptimeErrorEnum uptimeErrorEnum) {
			this.uptimeErrorEnum = uptimeErrorEnum;
		}

		@Override
		public String getCode() {
			return uptimeErrorEnum.getCode();
		}

		@Override
		public int getHttpStatus() {
			return uptimeErrorEnum.getHttpStatus();
		}

		@Override
		public String getDescription() {
			return uptimeErrorEnum.getDescription();
		}

		@Override
		public int hashCode() {
			return UptimeError.EQUIVALENCE.hash(this);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof UptimeError)) {
				return false;
			}
			UptimeError other = (UptimeError) obj;
			return UptimeError.EQUIVALENCE.equivalent(this, other);
		}

		@Override
		public String toString() {
			return com.google.common.base.Objects.toStringHelper(this).add("code", uptimeErrorEnum.getCode())
					.add("httpStatus", uptimeErrorEnum.getHttpStatus()).add("description", uptimeErrorEnum.getDescription())
					.toString();
		}

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

	public UptimeError asUptimeError() {
		return uptimeError;
	}

}
