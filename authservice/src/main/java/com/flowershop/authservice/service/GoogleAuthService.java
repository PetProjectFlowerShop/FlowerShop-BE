package com.flowershop.authservice.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flowershop.authservice.dto.GoogleLoginOrRegisterDTO;
import com.flowershop.authservice.exceptions.InvalidGoogleTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Value;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    @Value("${google.clientId}")
    private String clientId;

    public GoogleLoginOrRegisterDTO loginWithGoogle(String credential) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory()).setAudience(List.of(clientId))
                .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(credential);
        } catch (GeneralSecurityException | IOException e) {
            throw new InvalidGoogleTokenException("Unauthorized");
        }
        if (idToken == null) {
            throw new InvalidGoogleTokenException("Unauthorized");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String providerId = payload.getSubject();
        String email = payload.getEmail();
        String givenName = (String) payload.getOrDefault("given_name", "");
        String familyName = (String) payload.getOrDefault("family_name", "");
        Boolean emailVerified = payload.getEmailVerified();

        if (!Boolean.TRUE.equals(emailVerified)) {
            throw new InvalidGoogleTokenException("Unauthorized");
        }

        return new GoogleLoginOrRegisterDTO(
                providerId,
                givenName,
                familyName,
                email);
    }

}
