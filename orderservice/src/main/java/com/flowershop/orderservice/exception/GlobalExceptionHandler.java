package com.flowershop.orderservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ErrorResponse error(String message, Map<String, String> details) {
        return new ErrorResponse(message, details, LocalDateTime.now());
    }
}
