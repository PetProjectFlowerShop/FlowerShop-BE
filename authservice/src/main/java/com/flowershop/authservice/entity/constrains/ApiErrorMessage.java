package com.flowershop.authservice.entity.constrains;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ApiErrorMessage {
    USER_NOT_FOUND_BY_EMAIL("User with email: %s not found"),
    INVALID_PASSWORD("Invalid password for user: %s");
    private final String message;
    public String getMessage(Object... args){
        return String.format(message,args);
    }
}
