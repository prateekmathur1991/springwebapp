package com.springwebapp.dao;

import com.springwebapp.entity.Admin;

/**
 * The DAO interface for Admin entity
 */
public interface AdminDao {

    Admin findById(Long id);

    Admin findByUsername(String username);

    Admin findByUsernameAndPassword(String username, String password);

    Admin save(Admin admin);
}
