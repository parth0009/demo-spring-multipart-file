package com.demo.controller;

import org.springframework.validation.Errors;
import com.demo.model.Tester;

public abstract class TesterAction {
	
    protected void performCustomValidation(Tester tester, Errors errors) {
        TesterValidator validator = new TesterValidator();
        validator.validate(tester, errors);
    }

}
