package com.flowershop.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginRequest implements Serializable {
    @NotBlank(message = "Email must be present")
    private String email;
    @NotBlank(message = "Password must be present")
    private String password;
}
