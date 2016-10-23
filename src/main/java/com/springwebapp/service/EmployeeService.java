package com.springwebapp.service;

import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.entity.Employee;

/**
 * The service interface for Employee entity
 */
public interface EmployeeService {

    void saveWithDTO(EmployeeDTO employeeDTO);

    void save(Employee employee);

    Employee getEmployee(Long employeeId);

    void update(Employee employee);

    void delete(Long employeeId);
}
