package com.springwebapp.entity;

import com.springwebapp.dto.EmployeeDTO;

import javax.persistence.*;

/**
 * The entity to represent an Employee
 *
 * @author Prateek Mathur
 */

@Entity
@Table(name = "employee")
public class Employee {

    public Employee(EmployeeDTO employeeDTO)    {
        this.firstName = employeeDTO.firstName;
        this.lastName = employeeDTO.lastName;
        this.salary = employeeDTO.salary;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private Integer salary;

    public Employee() {
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
