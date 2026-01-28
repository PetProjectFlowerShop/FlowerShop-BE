package com.flowershop.authservice.controller;

import com.flowershop.authservice.dto.LoginRequest;
import com.flowershop.authservice.dto.LoginResponseDto;
import com.flowershop.authservice.dto.MyResponse;
import com.flowershop.authservice.exceptions.BadCredentialsException;
import com.flowershop.authservice.service.RateLimitingService;
import com.flowershop.authservice.dto.PasswordRecoveryRequest;
import com.flowershop.authservice.dto.PasswordResetDto;
import com.flowershop.authservice.dto.RegisterRequest;
import com.flowershop.authservice.dto.AuthResponse;
import com.flowershop.authservice.service.PasswordRecoveryService;
import com.flowershop.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RateLimitingService rateLimitingService;

    @PostMapping("/login")
    public ResponseEntity<MyResponse<?>> login(@RequestBody LoginRequest request) {
        if (rateLimitingService.isBlocked(request.getEmail())) {
            MyResponse<Void> myResponse = MyResponse.creteError("Too many attempts");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(myResponse);
        }
        try {
            LoginResponseDto loginResponseDto = userService.login(request);
            MyResponse<LoginResponseDto> myResponse = MyResponse.createSuccess(loginResponseDto);
            rateLimitingService.resetAttempts(request.getEmail());
            return ResponseEntity.ok(myResponse);
        }
        catch (BadCredentialsException e){
            rateLimitingService.recordFailedAttempts(request.getEmail());
            throw e;
        }
    private final PasswordRecoveryService passwordRecoveryService;

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
