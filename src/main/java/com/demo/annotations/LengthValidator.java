package com.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthValidator implements ConstraintValidator<LengthConstraint, String> {

    private int minLength;
    private int maxLength;
    private Class<?> type;

    @Override
    public void initialize(LengthConstraint constraint) {
        this.minLength = constraint.minLength();
        this.maxLength = constraint.maxLength();
        this.type = constraint.type();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {   	
    	if (value == null || value == "") {
    		return true;
    	}
    	
        if (value.length() < minLength || value.length() > maxLength) {
            //context.disableDefaultConstraintViolation();
            //context.buildConstraintViolationWithTemplate("Length is not within the specified range.")
            //        .addConstraintViolation();
            return false;
        }

        if (type.equals(Integer.class)) {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
              //  context.disableDefaultConstraintViolation();
             //   context.buildConstraintViolationWithTemplate("Value is not of type Integer.")
             //           .addConstraintViolation();
                return false;
            }
        } else if (type.equals(Long.class)) {
            try {
                Long.parseLong(value);
            } catch (NumberFormatException e) {
              //  context.disableDefaultConstraintViolation();
              //  context.buildConstraintViolationWithTemplate("Value is not of type Long.")
              //          .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
