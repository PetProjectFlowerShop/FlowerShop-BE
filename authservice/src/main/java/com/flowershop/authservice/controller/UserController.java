package com.flowershop.authservice.controller;

import com.flowershop.authservice.dto.LoginRequest;
import com.flowershop.authservice.dto.LoginResponseDto;
import com.flowershop.authservice.dto.MyResponse;
import com.flowershop.authservice.exceptions.BadCredentialsException;
import com.flowershop.authservice.exceptions.NotFoundException;
import com.flowershop.authservice.service.RateLimitingService;
import com.flowershop.authservice.dto.PasswordRecoveryRequest;
import com.flowershop.authservice.dto.PasswordResetDto;
import com.flowershop.authservice.dto.RegisterRequest;
import com.flowershop.authservice.dto.AuthResponse;
import com.flowershop.authservice.dto.GoogleLoginRequest;
import com.flowershop.authservice.service.PasswordRecoveryService;
import com.flowershop.authservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RateLimitingService rateLimitingService;
    private final PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<MyResponse<?>> login(@Valid @RequestBody LoginRequest request) {
        String email = request.getEmail();

        if (email != null && rateLimitingService.isBlocked(email)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(MyResponse.creteError("Too many attempts"));
        }

        try {
            LoginResponseDto loginResponseDto = userService.login(request);

            if (email != null) {
                rateLimitingService.resetAttempts(email);
            }
            return ResponseEntity.ok(MyResponse.createSuccess(loginResponseDto));

        } catch (BadCredentialsException | NotFoundException e) {
            if (email != null) {
                rateLimitingService.recordFailedAttempts(email);
            }
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> loginWithGoogle(@RequestBody GoogleLoginRequest request) {
        return ResponseEntity.ok(userService.loginOrRegisterWithGoogle(request.credential()));
    }

    @PostMapping("/password-recovery/request")
    @ResponseStatus(HttpStatus.OK)
    public void requestPasswordRecovery(@Valid @RequestBody PasswordRecoveryRequest request) {
        passwordRecoveryService.requestPasswordRecovery(request.email());
    }

    @PostMapping("/password-recovery/confirm/{token}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmPasswordRecovery(@PathVariable String token, @RequestBody @Valid PasswordResetDto passwordResetDto) {
        passwordRecoveryService.confirmPassword(token, passwordResetDto);
    }
}
