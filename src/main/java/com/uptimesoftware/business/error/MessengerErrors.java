package com.uptimesoftware.business.error;

import com.uptimesoftware.util.jsonrpc.JsonRpcResponseErrorCode;

public class MessengerErrors {
	public static final int MessengerCommunicationErrorCodeInt = JsonRpcResponseErrorCode.MESSENGER_EXCEPTION.getCode();
	public static final String MessengerCommunicationErrorCode = String.format("UT%d", MessengerCommunicationErrorCodeInt);
}
