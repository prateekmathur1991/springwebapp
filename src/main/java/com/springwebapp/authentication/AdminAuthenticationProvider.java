package com.springwebapp.authentication;

import com.springwebapp.entity.Admin;
import com.springwebapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A custom AuthenticationProvider for Admin.
 *
 * This class provides authentication service to query the login details of an admin.
 */

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminService adminService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Admin admin = adminService.findByUsernameAndPassword(String.valueOf(authentication.getPrincipal()), String.valueOf(authentication.getCredentials()));
        if (admin == null)  {
            return null;
        }

        // TODO How to store and retrieve the authorities of a user from a DB??
        List<GrantedAuthority> authorities = Collections.unmodifiableList(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return authentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
