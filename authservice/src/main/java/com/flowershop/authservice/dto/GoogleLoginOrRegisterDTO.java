package com.flowershop.authservice.dto;

public record GoogleLoginOrRegisterDTO(
                String providerId,
                String firstName,
                String lastName,
                String email) {
}
