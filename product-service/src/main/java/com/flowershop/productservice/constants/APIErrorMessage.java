package com.flowershop.productservice.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum APIErrorMessage {
    PRODUCT_NOT_FOUND_BY_ID("Not found product by id : %s"),
    PRODUCT_IMAGE_NOT_FOUND_BY_ID("Not found product image by id : %s"),
    FLOWER_TYPE_NOT_FOUND_BY_ID("Not found flower type by id : %s"),
    COLOR_NOT_FOUND_BY_ID("Not found color  by id: %s"),
    BOUQUET_TYPE_NOT_FOUND_BY_ID("Not found bouquet type by id: %s");
    public final String message;

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
    }
