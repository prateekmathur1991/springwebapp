package com.springwebapp.service;

import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.entity.Employee;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * The service interface for Employee entity
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface EmployeeService {

    void saveWithDTO(EmployeeDTO employeeDTO);

    void save(Employee employee);

    Employee findById(Long employeeId);

    void update(Employee employee);

    void delete(Long employeeId);
}
