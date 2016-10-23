package com.springwebapp.dao;

import com.springwebapp.entity.Admin;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * The DAO implementation for Admin entity
 */
@Repository
public class AdminDaoImpl implements AdminDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Admin findById(Long id) {
        try {
            return (Admin) em.find(Admin.class, id);
        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.findById:: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        try {
            TypedQuery<Admin> query = em.createQuery(" From Admin where username = :uname and password = :pass", Admin.class);
            query.setParameter("uname", username);
            query.setParameter("pass", password);
            query.setMaxResults(1);

            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error in AdminDaoImpl.findByUsernameAndPassword:: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Admin save(Admin admin) {
        try {
            em.persist(admin);
        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.save:: " + e.getLocalizedMessage());
            return null;
        }

        return admin;
    }
}
