package com.flowershop.productservice.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeValidator.class)
@Repeatable(ValidRanges.class)
public @interface ValidRange {
    String message() default "Invalid range";

    String minField();
    String maxField();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
