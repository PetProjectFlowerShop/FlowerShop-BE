package com.flowershop.authservice.controller;

import com.flowershop.authservice.dto.user.UserRequest;
import com.flowershop.authservice.dto.user.UserResponseDto;
import com.flowershop.authservice.service.user.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;

    @GetMapping("/me")
    public UserResponseDto getUserById(@AuthenticationPrincipal String username) {
        return userProfileService.getUserProfile(username);
    }

    @PatchMapping("/me")
    public UserResponseDto updateUserProfile(
        @AuthenticationPrincipal String username,
        @Valid @RequestBody UserRequest request) {
        return userProfileService.updateUserProfile(username, request);
    }
}
