package com.example.finnybuddy.core.validation.annotations.validator;

import com.example.finnybuddy.core.validation.annotations.ValueValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidationValidator implements ConstraintValidator<ValueValidation, Object> {
    private int max;
    private int min;
    private String message;

    public CustomValidationValidator() {
    }

    @Override
    public void initialize(ValueValidation valueValidation) {
        this.max = valueValidation.max();
        this.min = valueValidation.min();
        this.message = valueValidation.message();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else if (value instanceof String stringValue) {
            if (stringValue.isEmpty()) {
                return true;
            } else {
                int length = stringValue.length();
                return this.isValidString(length, context);
            }
        } else if (value instanceof Number numberValue) {
            return this.isValidNumber(numberValue, context);
        } else {
            return true;
        }
    }

    private boolean isValidString(int length, ConstraintValidatorContext context) {
        if (length < this.min) {
            this.buildConstraintViolation(String.format("Value should be at least %d symbols long", this.min), context);
            return false;
        } else if (length > this.max) {
            this.buildConstraintViolation(String.format("Value should be no more than %d symbols long", this.max), context);
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidNumber(Number number, ConstraintValidatorContext context) {
        double numericValue = number.doubleValue();
        if (numericValue < this.min) {
            this.buildConstraintViolation(String.format("Value should be greater than %d", this.min), context);
            return false;
        } else if (numericValue > this.max) {
            this.buildConstraintViolation(String.format("Value should be less than %d", this.max), context);
            return false;
        } else {
            return true;
        }
    }

    private void buildConstraintViolation(String message, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}

