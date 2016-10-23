package com.springwebapp.service;

import com.springwebapp.dao.AdminDao;
import com.springwebapp.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service implementation for Admin
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin findById(Long id) {
        return adminDao.findById(id);
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return adminDao.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional
    public Admin save(Admin admin) {
        return adminDao.save(admin);
    }
}
