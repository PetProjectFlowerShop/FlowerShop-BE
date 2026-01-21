package com.flowershop.authservice.service;

import com.flowershop.authservice.entity.constrains.ApiErrorMessage;
import com.flowershop.authservice.dto.LoginRequest;
import com.flowershop.authservice.dto.LoginResponseDto;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.exceptions.BadCredentialsException;
import com.flowershop.authservice.exceptions.NotFoundException;
import com.flowershop.authservice.repository.UserRepository;
import com.flowershop.authservice.security.UsersDetails;
import com.flowershop.authservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService  {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


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
