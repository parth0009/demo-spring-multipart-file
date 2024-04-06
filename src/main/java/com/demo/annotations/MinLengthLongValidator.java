package com.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinLengthLongValidator implements ConstraintValidator<MinLengthLong, String> {

    @Override
    public void initialize(MinLengthLong constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	if (value == null || value.trim().length() == 0) {
    		return true;
    	}
        if (value.length() < 10) {
            return false;
        }
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}