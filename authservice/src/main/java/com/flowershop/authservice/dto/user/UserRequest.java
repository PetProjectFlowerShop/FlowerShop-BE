package com.flowershop.authservice.dto.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Size(min = 3, max = 200)
    private String fullName;

    @Size(min = 3, max = 30)
    private String phoneNumber;

    @Past
    private LocalDate dateOfBirth;
}
