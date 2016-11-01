package com.springwebapp.dao;

import com.springwebapp.entity.Admin;
import com.springwebapp.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * The DAO implementation for Employee entity
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Employee> list() {
        try {
            return em.createQuery(" From Employee", Employee.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error in EmployeeDaoImpl.list:: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Employee save(Employee employee) {
        try {
            em.persist(employee);
        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.save:: " + e.getLocalizedMessage());
            return null;
        }

        return employee;
    }

    @Override
    public Employee findById(Long employeeId) {
        try {
            return (Employee) em.find(Employee.class, employeeId);
        } catch (Exception e) {
            System.err.println("Exception in EmployeeDaoImpl.findById:: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void update(Employee employee) {
        em.merge(employee);
    }

    @Override
    public void delete(Long employeeId) {
        em.remove(em.find(Employee.class, employeeId));
    }
}
