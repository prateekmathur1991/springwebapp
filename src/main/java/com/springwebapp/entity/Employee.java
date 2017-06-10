package com.springwebapp.entity;

import com.springwebapp.dto.EmployeeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * The entity to represent an Employee
 *
 * @author Prateek Mathur
 */

@Entity
@Table(name = "employee")
@Getter @Setter
@NoArgsConstructor
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
}
