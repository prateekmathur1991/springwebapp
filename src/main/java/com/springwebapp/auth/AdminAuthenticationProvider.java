package com.springwebapp.auth;

import com.springwebapp.entity.Admin;
import com.springwebapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
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

        Admin admin = adminService.findByUsernameAndPassword(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        if (admin == null)  {
            return null;
        }

        GrantedAuthority authority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };

        List<GrantedAuthority> authorities = Collections.unmodifiableList(Arrays.asList(authority));

        return authentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
