package com.flowershop.authservice.service;

import com.flowershop.authservice.entity.emums.AuthProvider;
import com.flowershop.authservice.entity.emums.Role;
import com.flowershop.authservice.exceptions.EmailAlreadyExistsException;
import com.flowershop.authservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.flowershop.authservice.dto.RegisterRequest;
import com.flowershop.authservice.dto.AuthResponse;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                String.format("Email '%s' is already registered",
                    request.getEmail())
            );
        }

        User user = User.builder()
            .firstname(request.getFirstName())
            .lastname(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .authProvider(AuthProvider.LOCAL)
            .role(Role.USER)
            .isMarketingAllowed(request.getIsMarketingAllow())
            .build();

        userRepository.save(user);

        return new AuthResponse(jwtUtils.generateToken(user.getEmail()));
    }
}
