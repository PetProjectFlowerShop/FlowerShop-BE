package com.flowershop.authservice.utils;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class TokenEncryption {

    public String hashToken (String rawToken) {
        String hashToken;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte [] hash = digest.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            hashToken = new String(hash);
        } catch (NoSuchAlgorithmException e){
            throw new IllegalArgumentException();
        }

        return hashToken;
    }
}
