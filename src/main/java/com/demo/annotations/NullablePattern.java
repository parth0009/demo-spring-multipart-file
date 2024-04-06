package com.demo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Size(min = 0)
@Pattern(regexp = "")
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullablePattern {
    String message() default "Invalid value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String regexp() default ".*"; // Default pattern that matches any string
    int minSize() default 0; // Default minimum size
}