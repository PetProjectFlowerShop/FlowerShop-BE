package com.flowershop.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PasswordResetDto(
    @NotBlank(message = "Token is required")
    String token,
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
        message = "Password must be at least 8 characters long, contain at least one uppercase letter and one digit")
    String newPassword) {
}
