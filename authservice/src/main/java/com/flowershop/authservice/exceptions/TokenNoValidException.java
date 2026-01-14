package com.flowershop.authservice.exceptions;

public class TokenNoValidException extends RuntimeException{
    public TokenNoValidException() {
        super("Invalid or expired token");
    }
}
