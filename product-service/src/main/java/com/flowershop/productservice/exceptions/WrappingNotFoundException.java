package com.flowershop.productservice.exceptions;

public class WrappingNotFoundException extends RuntimeException {
    public WrappingNotFoundException (String message) {
        super (message);
    }
}
