package com.demo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RequiredIntegerValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredInteger {
    String message() default "The field must be an integer and have a minimum length of 2";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}