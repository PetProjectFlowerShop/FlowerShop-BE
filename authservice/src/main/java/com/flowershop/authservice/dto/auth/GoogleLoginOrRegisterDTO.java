package com.flowershop.authservice.dto.auth;

public record GoogleLoginOrRegisterDTO(
                String providerId,
                String firstName,
                String lastName,
                String email) {
}
