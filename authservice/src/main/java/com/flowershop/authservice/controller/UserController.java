package com.flowershop.authservice.controller;

import com.flowershop.authservice.dto.PasswordRecoveryRequest;
import com.flowershop.authservice.dto.PasswordResetDto;
import com.flowershop.authservice.dto.RegisterRequest;
import com.flowershop.authservice.dto.AuthResponse;
import com.flowershop.authservice.service.PasswordRecoveryService;
import com.flowershop.authservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/password-recovery/request")
    @ResponseStatus(HttpStatus.OK)
    public void requestPasswordRecovery(@Valid @RequestBody PasswordRecoveryRequest request){
        passwordRecoveryService.requestPasswordRecovery(request.email());
    }

    @PostMapping("/password-recovery/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmPasswordRecovery(@Valid @RequestBody PasswordResetDto passwordResetDto){
        passwordRecoveryService.confirmPassword(passwordResetDto);
    }
}
