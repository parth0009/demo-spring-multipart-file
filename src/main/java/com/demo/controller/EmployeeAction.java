package com.demo.controller;

import org.springframework.validation.Errors;

import com.demo.model.Employee;

public abstract class EmployeeAction {
	
    protected void performCustomValidation(Employee emp, Errors errors) {
        EmployeeValidator validator = new EmployeeValidator();
        validator.validate(emp, errors);
    }

}
