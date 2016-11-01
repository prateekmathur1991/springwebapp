package com.springwebapp.authentication;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * A Token Manager abstracts the handles the creation
 * and removal of tokens, and also support querying users by
 * token.
 */
public interface TokenManager {

    /**
     * Creates a new token
     *
     * @param userDetails
     * @return The newly created token
     */
    String createNewToken(UserDetails userDetails);

    /**
     * Retrieves User details by token
     *
     * @param token
     * @return UserDetails corresponding to the token. {@code null} if no user was found
     */
    UserDetails getUserByToken(String token);

    /**
     * Removes the user details for the given token
     *
     * @param token
     * @return UserDetails corresponding to the token. {@code null} if no user was found
     */
    UserDetails removeUser(String token);
}
