package com.demo.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinLengthLongValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLengthLong {
    String message() default "The field must be long and have a minimum length of 2";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}