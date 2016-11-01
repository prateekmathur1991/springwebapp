package com.springwebapp.controller;

import com.springwebapp.authentication.AuthenticationService;
import com.springwebapp.entity.Admin;
import com.springwebapp.entity.Employee;
import com.springwebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees()    {
        Admin currentUser = (Admin) authenticationService.getLoggedInUser();    // Just to make a point :)

        return employeeService.list();
    }
}
