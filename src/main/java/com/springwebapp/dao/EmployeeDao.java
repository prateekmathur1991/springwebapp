package com.springwebapp.dao;

import com.springwebapp.entity.Employee;

/**
 * The DAO interface for Employee entity
 */
public interface EmployeeDao {

    Employee save(Employee employee);

    Employee findById(Long employeeId);

    void update(Employee employee);

    void delete(Long employeeId);
}
