package com.springwebapp.authentication.impl;

import com.springwebapp.authentication.AuthenticationService;
import com.springwebapp.authentication.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Concrete implementation of {@link com.springwebapp.authentication.AuthenticationService}.
 *
 * Its most important job is to act as a glue between Spring's {@link org.springframework.security.authentication.AuthenticationManager}
 * and our {@link TokenManager}.
 */
@Component
public class RESTAuthenticationService implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public String authenticate(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authentication = authenticationManager.authenticate(authentication);
            // Here principal=UserDetails (UserContext in our case), credentials=null (security reasons)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getPrincipal() != null) {
                UserDetails userContext = (UserDetails) authentication.getPrincipal();
                String newToken = tokenManager.createNewToken(userContext);
                if (newToken == null) {
                    return null;
                }
                return newToken;
            }
        } catch (AuthenticationException e) {
            System.out.println(" *** AuthenticationServiceImpl.authenticate - FAILED: " + e.toString());
        }
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        UserDetails userDetails = tokenManager.getUserByToken(token);
        if (userDetails == null) {
            return false;
        }

        SecurityContextHolder.getContext().setAuthentication(
                new PreAuthenticatedAuthenticationToken(userDetails, null, userDetails.getAuthorities()));

        return true;
    }

    @Override
    public void logout(String token) {
        tokenManager.removeUser(token);
        SecurityContextHolder.clearContext();
    }

    @Override
    public UserDetails getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        return (UserDetails) authentication.getPrincipal();
    }
}
