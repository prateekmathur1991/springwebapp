package com.springwebapp.service;

import com.springwebapp.dao.EmployeeDao;
import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The service implementation for Employee entity
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Employee> list() {
        return employeeDao.list();
    }

    @Override
    @Transactional
    public void saveWithDTO(EmployeeDTO employeeDTO) {
        employeeDao.save(new Employee(employeeDTO));
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long employeeId) {
        return employeeDao.findById(employeeId);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    @Transactional
    public void delete(Long employeeId) {
        employeeDao.delete(employeeId);
    }
}
