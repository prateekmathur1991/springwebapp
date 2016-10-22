package com.springwebapp.service;

import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.entity.Employee;

/**
 * Created by prateek on 25/9/16.
 */
public interface EmployeeService {

    void saveWithDTO(EmployeeDTO employeeDTO);

    void save(Employee employee);

    Employee getEmployee(Long employeeId);

    void update(Employee employee);

    void delete(Long employeeId);
}
