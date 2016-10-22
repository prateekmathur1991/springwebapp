package com.springwebapp.service;

import com.springwebapp.dao.EmployeeDao;
import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by prateek on 25/9/16.
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void saveWithDTO(EmployeeDTO employeeDTO) {
        employeeDao.save(new Employee(employeeDTO));
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeDao.getEmployee(employeeId);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Long employeeId) {
        employeeDao.delete(employeeId);
    }
}
