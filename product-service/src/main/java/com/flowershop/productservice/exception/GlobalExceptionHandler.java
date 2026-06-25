package com.flowershop.productservice.exception;

import com.flowershop.productservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(error ->
            errors.put(error.getObjectName(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
            new ErrorResponse(
                "Validation failed",
                errors,
                LocalDateTime.now()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
        String errorMessage = String.format("Parameter '%s' of type '%s' is required",
            ex.getParameterName(),
            ex.getParameterType());
        Map<String, String> details = new HashMap<>();
        details.put("requiredParameter", ex.getParameterName());
        details.put("expectedType", ex.getParameterType());

        ErrorResponse response = new ErrorResponse(
            errorMessage,
            details,
            LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(
            new ErrorResponse(
                ex.getMessage(),
                Collections.emptyMap(),
                LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(
                ex.getMessage(),
                Collections.emptyMap(),
                LocalDateTime.now()
            ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorResponse(
                ex.getMessage(),
                null,
                LocalDateTime.now()));
    }
}
