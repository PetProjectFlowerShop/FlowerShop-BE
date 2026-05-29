package com.flowershop.productservice.dto.validation;

import com.flowershop.productservice.dto.ProductFilterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceRangeValidator implements ConstraintValidator<ValidPriceRange, ProductFilterRequest> {
    @Override
    public boolean isValid(ProductFilterRequest value, ConstraintValidatorContext context) {
        if (value.getMinPrice() == null || value.getMaxPrice() == null) {
            return true;
        }
        return value.getMinPrice().compareTo(value.getMaxPrice()) <= 0;
    }
}
