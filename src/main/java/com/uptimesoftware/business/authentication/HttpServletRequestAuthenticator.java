package com.uptimesoftware.business.authentication;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

public interface HttpServletRequestAuthenticator {

	/**
	 * Return the current user ID from the given HttpServletRequest. Returns null if no user ID was found. Throws
	 * AuthenticationException if an error occurred while trying to get the ID.
	 */
	@Nullable
	Long getCurrentUserId(@Nonnull HttpServletRequest request) throws AuthenticationException;

}
