package com.springwebapp.entity;

import com.springwebapp.dto.EmployeeDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The entity to represent an Employee
 *
 * @author Prateek Mathur
 */

@Entity
@Table(name = "employee")
public class Employee {

    public Employee() {}

    public Employee(EmployeeDTO employeeDTO)    {
        this.firstName = employeeDTO.firstName;
        this.lastName = employeeDTO.lastName;
        this.salary = employeeDTO.salary;
    }

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private Integer salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
