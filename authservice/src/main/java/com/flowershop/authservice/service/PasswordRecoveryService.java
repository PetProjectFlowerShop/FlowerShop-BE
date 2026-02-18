package com.flowershop.authservice.service;

import com.flowershop.authservice.dto.PasswordResetDto;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.exceptions.EmailNotRegisteredException;
import com.flowershop.authservice.exceptions.TokenNoValidException;
import com.flowershop.authservice.repository.UserRepository;
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
    @Value("${app.password.recovery.host}")
    private String host;
    @Value("${app.password.recovery.path}")
    private String recoveryPath;

    public void requestPasswordRecovery(String email){
        if(!userRepository.existsByEmail(email)){
            throw new EmailNotRegisteredException(email);
        }

        String token = UUID.randomUUID().toString();
        String url = host + recoveryPath + token;
        System.out.println("url = " + url);
        redisTemplate.opsForValue().set(token, email, Duration.ofMinutes(15));
    }

    @Transactional
    public void confirmPassword(String token, PasswordResetDto passwordResetDto){
        String newPassword = passwordResetDto.newPassword();
        String email = redisTemplate.opsForValue().getAndDelete(token);
        if (email == null){
            throw new TokenNoValidException();
        }

        User user = userRepository.findUserByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
