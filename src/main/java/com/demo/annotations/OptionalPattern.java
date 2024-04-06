package com.demo.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalPatternValidator.class)
@ReportAsSingleViolation
public @interface OptionalPattern {
	String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String regexp();
    /**
     * Whether the value can be null or an empty string.
     * Default is false.
     */
    boolean optional() default true;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        OptionalPattern[] value();
    }
}