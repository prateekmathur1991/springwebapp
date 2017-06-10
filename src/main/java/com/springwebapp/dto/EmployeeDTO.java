package com.springwebapp.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The DTO (Data Transfer Object) for an Employee entity
 * Used to read the parameters sent from the client side
 *
 * @author Prateek Mathur
 */

public class EmployeeDTO {

    @NotNull
    @NotEmpty
    @Size(max = 100, message = "Length of this field cannot be greater than {max}")
    public String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 100, message = "Length of this field cannot be greater than {max}")
    public String lastName;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(1)
    public Integer salary;

    public EmployeeDTO() {
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
