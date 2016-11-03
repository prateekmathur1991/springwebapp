package com.springwebapp.authentication;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * A service that interacts with Spring Security.
 * Every component that needs interaction with Spring Security should use this service.
 *
 * This interface does not deal with HTTP (or any communication protocol) at all.
 */
public interface AuthenticationService {

    /**
     * Authenticates an incoming user and returns a valid token. Also prepares a SecurityContext
     * on successful authentication.
     *
     * @param username
     * @param password
     * @return A valid token upon successful authentication. {@code null} if authentication failed
     */
    String authenticate(String username, String password);

    /**
     * Checks if the token is valid.
     * Updates the {@link org.springframework.security.core.context.SecurityContext} in that case,
     * and returns true.
     *
     * @param token
     * @return true if token is valid, false otherwise
     */
    boolean checkToken(String token);

    /**
     * Logs out the user, and invalidates the token. Also clears the
     * {@link org.springframework.security.core.context.SecurityContext} in case of successful logout.
     *
     * This method must be called by an already authenticated user.
     *
     * @param token
     */
    void logout(String token);

    /**
     * Convenience method to get the currently logged in user. Returns {@link UserDetails} of the
     * user, so cast to application-specific class will be needed.
     *
     * @return
     */
    UserDetails getLoggedInUser();
}
