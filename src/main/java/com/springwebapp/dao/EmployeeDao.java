package com.springwebapp.dao;

import com.springwebapp.entity.Employee;

import java.util.List;

/**
 * The DAO interface for Employee entity
 */
public interface EmployeeDao {

    List<Employee> list();

    Employee save(Employee employee);

    Employee findById(Long employeeId);

    void update(Employee employee);

    void delete(Long employeeId);
}
