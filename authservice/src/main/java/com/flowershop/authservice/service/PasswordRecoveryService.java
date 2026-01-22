package com.flowershop.authservice.service;

import com.flowershop.authservice.dto.PasswordResetDto;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.exceptions.EmailNotRegisteredException;
import com.flowershop.authservice.exceptions.TokenNoValidException;
import com.flowershop.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public void requestPasswordRecovery(String email){
        if(!userRepository.existsByEmail(email)){
            throw new EmailNotRegisteredException(email);
        }

        String token = UUID.randomUUID().toString();
        System.out.println("Token = " + token);
        redisTemplate.opsForValue().set(token, email, Duration.ofMinutes(15));
    }

    @Transactional
    public void confirmPassword(PasswordResetDto passwordResetDto){
        String token = passwordResetDto.token();
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
