package com.flowershop.authservice.dto;

public record GoogleLoginOrRegisterRequest(
        String providerId,
        String firstName,
        String lastName,
        String email) {
}
