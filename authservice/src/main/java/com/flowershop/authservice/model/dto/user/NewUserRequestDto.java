package com.flowershop.authservice.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserRequestDto implements Serializable {
@NotBlank(message = "First name must be present")
    private String firstName;
    private String lastName;
    @NotBlank(message = "First name must be present")
    private String email;
    @NotBlank(message = "First name must be present")
    private String password;
}
