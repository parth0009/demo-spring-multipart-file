package com.demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.model.Employee;

@Component
public class EmployeeValidator implements Validator {
	
    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

	
    @Override
    public void validate(Object target, Errors errors) {
    	System.out.println("Coming in the validate method");
        //super.validate(target, errors);
            if (target instanceof Employee) {
            	Employee targetForm = (Employee) target;
            	targetForm.validate(targetForm, errors);
                //String code = message.getKey(); // TODO Prefix with "EForm" or whatever package
                //errors.rejectValue(field, this.bundlePrefix + "." + code, message.values, "Unknown Field Error");
           

        }
    }

}
