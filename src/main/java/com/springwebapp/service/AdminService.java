package com.springwebapp.service;

import com.springwebapp.entity.Admin;

/**
 * The service interface for Admin
 */
public interface AdminService {

    Admin findById(Long id);

    Admin findByUsernameAndPassword(String username, String password);

    Admin save(Admin admin);
}
