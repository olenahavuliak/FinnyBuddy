package com.example.finnybuddy.core.validation.annotations.validator;

import com.example.finnybuddy.core.validation.annotations.RequiredField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredFieldValidator implements ConstraintValidator<RequiredField, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value != null) {
            if (value instanceof String string) {
                return !(string.isEmpty());
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
