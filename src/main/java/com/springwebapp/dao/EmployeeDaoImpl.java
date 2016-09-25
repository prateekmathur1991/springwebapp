package com.springwebapp.dao;

import com.springwebapp.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by prateek on 25/9/16.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Employee employee) {
        em.persist(employee);
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return (Employee) em.find(Employee.class, employeeId);
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
