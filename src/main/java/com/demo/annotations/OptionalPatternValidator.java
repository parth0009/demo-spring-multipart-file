package com.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionalPatternValidator implements ConstraintValidator<OptionalPattern, String> {

	private java.util.regex.Pattern pattern;
    private boolean optional;

    @Override
    public void initialize(OptionalPattern constraint) {
    	pattern = java.util.regex.Pattern.compile(constraint.regexp());
        optional = constraint.optional();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {   	
    	
    	if (value == null || value.length() == 0) {
            return optional; // Return true if optional and value is null or empty
        }
        return pattern.matcher(value).matches();
    }
}
