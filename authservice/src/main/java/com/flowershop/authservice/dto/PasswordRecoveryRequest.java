package com.flowershop.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PasswordRecoveryRequest(
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email) {
}
