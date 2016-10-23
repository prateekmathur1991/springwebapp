/**
 * Handles requests sent for all employees
 *
 * @author Prateek Mathur
 */

package com.springwebapp.controller;

import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/admin/addEmployee")
    public ModelAndView addEmployeePage() {

        return new ModelAndView("addEmployee", "AddEmployeeForm", new EmployeeDTO());
    }

    @PostMapping("/admin/addEmployee")
    public ModelAndView addEmployeeAction(@Valid @ModelAttribute("AddEmployeeForm") EmployeeDTO employeeDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("addEmployee");
            model.addObject("AddEmployeeForm", employeeDTO);

            return model;
        }

        Boolean saveResult = Boolean.FALSE;
        try {
            employeeService.saveWithDTO(employeeDTO);
            saveResult = Boolean.TRUE;
        } catch (Exception e) {
            System.err.println("Exception in EmployeeController.addEmployeeAction when saving employee:: " + e.getLocalizedMessage());
        }

        ModelAndView model = new ModelAndView("addEmployee");
        model.addObject("AddEmployeeForm", employeeDTO);
        model.addObject("saveResult", saveResult);

        return model;
    }
}
