package com.uptimesoftware.business.authentication;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Optional;

public interface HttpServletRequestAuthenticator {

	/**
	 * Return the current user ID from the given HttpServletRequest. Throws AuthenticationException if an error occurred while
	 * trying to get the ID.
	 */
	Optional<Long> getCurrentUserId(@Nonnull HttpServletRequest request) throws AuthenticationException;

}
