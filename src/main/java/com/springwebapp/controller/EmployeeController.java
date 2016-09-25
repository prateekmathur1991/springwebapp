package com.springwebapp.controller;

import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests sent for all employees
 *
 * @author Prateek Mathur
 */

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/admin/addEmployee", method = RequestMethod.GET)
    public ModelAndView addEmployeePage()   {

        ModelAndView model = new ModelAndView();
        model.setViewName("addEmployee");

        model.addObject("AddEmployeeForm", new EmployeeDTO());

        return model;
    }

    @RequestMapping(value = "/admin/addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployeeAction(@ModelAttribute(value = "AddEmployeeForm") EmployeeDTO employeeDTO) {

        employeeService.saveWithDTO(employeeDTO);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addEmployee");

        return modelAndView;
    }
}
