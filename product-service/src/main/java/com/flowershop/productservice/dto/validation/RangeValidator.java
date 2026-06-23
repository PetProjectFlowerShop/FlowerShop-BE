package com.flowershop.productservice.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class RangeValidator implements ConstraintValidator<ValidRange, Object> {
    private String minField;
    private String maxField;

    @Override
    public void initialize(ValidRange constraintAnnotation) {
        this.minField = constraintAnnotation.minField();
        this.maxField = constraintAnnotation.maxField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field minFieldObj = value.getClass().getDeclaredField(minField);
            Field maxFieldObj = value.getClass().getDeclaredField(maxField);
            minFieldObj.setAccessible(true);
            maxFieldObj.setAccessible(true);
            Object minValue = minFieldObj.get(value);
            Object maxValue = maxFieldObj.get(value);
            if (minValue == null || maxValue == null) {
                return true; // игнорируем
            }

            Comparable min = (Comparable) minValue;
            Comparable max = (Comparable) maxValue;

            if (min.compareTo(max) > 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(minField)
                    .addConstraintViolation();
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
