package com.springwebapp.authentication;

import com.springwebapp.entity.Admin;
import com.springwebapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * A custom implementation of {@link UserDetailsService} that can load an admin
 * user from the DB
 */
@Component
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findByUsername(username);
         if (admin == null)  {
            throw new UsernameNotFoundException("No user exists with this username");
        }

        return admin;
    }
}
