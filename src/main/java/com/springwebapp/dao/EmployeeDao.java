package com.springwebapp.dao;

import com.springwebapp.entity.Employee;

/**
 * Created by prateek on 25/9/16.
 */
public interface EmployeeDao {

    void save(Employee employee);

    Employee getEmployee(Long employeeId);

    void update(Employee employee);

    void delete(Long employeeId);
}
