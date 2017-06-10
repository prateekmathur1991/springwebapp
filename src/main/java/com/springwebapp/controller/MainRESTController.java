package com.springwebapp.controller;

import com.springwebapp.authentication.AuthenticationService;
import com.springwebapp.entity.Employee;
import com.springwebapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller which is a REST API.
 */
@RestController
@RequestMapping("/rest")
public class MainRESTController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmployeeService employeeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainRESTController.class);

    @PostMapping("/getEmployees")
    public List<Employee> getEmployees()    {

        LOGGER.debug("getEmployees() is executed, value {}", "springwebapp");

        List<Employee> employees = employeeService.list();
        if (employees.isEmpty()) {
            LOGGER.error("No employees!!", new Exception("List is Empty"));
        }

        return employees;
    }
}
