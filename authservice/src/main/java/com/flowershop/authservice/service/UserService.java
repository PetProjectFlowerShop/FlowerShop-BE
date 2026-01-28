package com.flowershop.authservice.service;

import com.flowershop.authservice.dto.AuthResponse;
import com.flowershop.authservice.dto.RegisterRequest;
import com.flowershop.authservice.entity.constrains.ApiErrorMessage;
import com.flowershop.authservice.dto.LoginRequest;
import com.flowershop.authservice.dto.LoginResponseDto;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.entity.emums.AuthProvider;
import com.flowershop.authservice.entity.emums.Role;
import com.flowershop.authservice.exceptions.BadCredentialsException;
import com.flowershop.authservice.exceptions.EmailAlreadyExistsException;
import com.flowershop.authservice.exceptions.NotFoundException;
import com.flowershop.authservice.repository.UserRepository;
import com.flowershop.authservice.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService  {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


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
            .role(Role.ROLE_USER)
            .isMarketingAllowed(request.getIsMarketingAllow())
            .build();
        userRepository.save(user);
        return new AuthResponse(jwtUtils.generateToken(user.getEmail()));
    }

    public LoginResponseDto login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() ->
                new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(request.getEmail())));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(ApiErrorMessage.INVALID_PASSWORD.getMessage(request.getEmail()));
        }
        String jwtToken = jwtUtils.generateToken(request.getEmail());
        return new LoginResponseDto(jwtToken, user.getRole().name());

    }
}
