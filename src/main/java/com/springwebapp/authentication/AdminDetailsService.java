package com.springwebapp.authentication;

import com.springwebapp.entity.Admin;
import com.springwebapp.entity.AdminRole;
import com.springwebapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A custom implementation of {@link UserDetailsService} that can load an admin
 * user from the DB
 */
@Component
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findByUsername(username);
        if (admin == null)  {
            throw new UsernameNotFoundException("No user exists with this username");
        }

        return new User(username, admin.getPassword(), buildAuthorities(admin.getAdminRoles()));
    }

    private List<GrantedAuthority> buildAuthorities(Set<AdminRole> adminRoles)    {

        if (adminRoles == null)  {
            return Collections.emptyList();
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (AdminRole role : adminRoles)    {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }
}
