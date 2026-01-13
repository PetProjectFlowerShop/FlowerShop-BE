package com.flowershop.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁёЇїІіЄєҐґ'\\-\\s]{2,50}$",
        message = "First name must be 2-50 characters long and contain only letters")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^$|^[A-Za-zА-Яа-яЁёЇїІіЄєҐґ'\\-\\s]{0,50}$",
        message = "Last name must be empty or 2-50 characters long and contain only letters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
        message = "Password must be at least 8 characters long, contain at least one uppercase letter and one digit")
    private String password;

    @NotNull(message = "Marketing permission is required")
    private Boolean isMarketingAllow;
}
