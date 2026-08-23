package com.flowershop.authservice.service;

import com.flowershop.authservice.dto.PasswordResetDto;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.exceptions.EmailNotRegisteredException;
import com.flowershop.authservice.exceptions.TokenNoValidException;
import com.flowershop.authservice.repository.UserRepository;
import com.flowershop.authservice.utils.TokenEncryption;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final StringRedisTemplate redisTemplate;
    private final TokenEncryption tokenEncryption;
    private final EmailSendingService emailSendingService;

    private final String TOKEN_PARAM_NAME = "?token=";
    @Value("${app.password.recovery.host}")
    private String passwordRecoveryHost;
    @Value("${app.password.recovery.path}")
    private String passwordRecoveryPath;



    public void requestPasswordRecovery(String email){
        if(!userRepository.existsByEmail(email)){
            throw new EmailNotRegisteredException(email);
        }

        String token = UUID.randomUUID().toString();
        String hashToken = tokenEncryption.hashToken(token);
        redisTemplate.opsForValue().set(hashToken, email, Duration.ofMinutes(15));
        String fullLink = passwordRecoveryHost + passwordRecoveryPath + TOKEN_PARAM_NAME + token;
        emailSendingService.sendEmailForPasswordRecovery(email, fullLink);
    }

    @Transactional
    public void confirmPassword(String token, PasswordResetDto passwordResetDto){
        String newPassword = passwordResetDto.newPassword();
        String hashToken = tokenEncryption.hashToken(token);
        String email = redisTemplate.opsForValue().getAndDelete(hashToken);
        if (email == null){
            throw new TokenNoValidException();
        }

        User user = userRepository.findUserByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
