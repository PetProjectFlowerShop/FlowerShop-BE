package com.flowershop.productservice.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException (String message) {
        super(message);
    }
}
