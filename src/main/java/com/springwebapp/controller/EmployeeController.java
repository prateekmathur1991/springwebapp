/**
 * Handles requests sent for all employees
 *
 * @author Prateek Mathur
 */

package com.springwebapp.controller;

import com.springwebapp.dto.EmployeeDTO;
import com.springwebapp.entity.Employee;
import com.springwebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

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

    @GetMapping("/admin/viewEmployees")
    public ModelAndView viewEmployees() {

        List<Employee> employees = employeeService.list();
        if (employees == null) {
            employees = Collections.emptyList();
        }

        return new ModelAndView("view-employees", "employees", employees);
    }

    @GetMapping("/admin/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long id) {

        employeeService.delete(id);

        RedirectView redirectView = new RedirectView("/admin/viewEmployees");
        redirectView.setContextRelative(true);

        return redirectView;
    }

    @GetMapping("/admin/edit/{id}")
    public RedirectView edit(@PathVariable("id") Long id) {

        Employee employee = employeeService.findById(id);
        employee.setFirstName("Prateek Update");
        employee.setLastName("Mathur Update");
        employee.setSalary(100000);                                                                          ;

        employeeService.update(employee);

        RedirectView redirectView = new RedirectView("/admin/viewEmployees");
        redirectView.setContextRelative(true);

        return redirectView;
    }
}
