package com.example.finnybuddy.core.validation.annotations;

import com.example.finnybuddy.core.validation.annotations.validator.RequiredFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {RequiredFieldValidator.class})
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredField {
    String message() default "Value should not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
