package com.springwebapp.service;

import com.springwebapp.dao.EmployeeDao;
import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service implementation for Employee entity
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

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
    public Employee getEmployee(Long employeeId) {
        return employeeDao.getEmployee(employeeId);
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
