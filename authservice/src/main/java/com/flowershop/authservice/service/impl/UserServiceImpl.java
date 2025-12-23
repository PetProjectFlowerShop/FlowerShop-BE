package com.flowershop.authservice.service.impl;

import com.flowershop.authservice.model.constrains.ApiErrorMessage;
import com.flowershop.authservice.model.dto.user.request.LoginUserRequestDto;
import com.flowershop.authservice.model.dto.user.request.NewUserRequestDto;
import com.flowershop.authservice.model.dto.user.response.LoginResponseDto;
import com.flowershop.authservice.model.entity.User;
import com.flowershop.authservice.model.exceptions.BadCredentialsException;
import com.flowershop.authservice.model.exceptions.NotFoundException;
import com.flowershop.authservice.repository.UserRepository;
import com.flowershop.authservice.service.UserService;
import com.flowershop.authservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public LoginResponseDto login(LoginUserRequestDto request) {
        User user = findByEmail(request.getEmail())
            .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(request.getEmail())));
        if (!user.getPassword().equals(passwordEncoder.encode(request.getPassword()))) {
            throw new BadCredentialsException(ApiErrorMessage.INVALID_PASSWORD.getMessage(request.getEmail()));
        }
        String jwtToken= jwtUtils.generateToken(request.getEmail());
        return new LoginResponseDto(jwtToken,user.getRole().name());

    }
}
