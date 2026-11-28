package com.flowershop.authservice.service.auth;

import com.flowershop.authservice.dto.auth.AuthResponse;
import com.flowershop.authservice.dto.auth.GoogleLoginOrRegisterDTO;
import com.flowershop.authservice.dto.auth.RegisterRequest;
import com.flowershop.authservice.exceptions.constrains.ApiErrorMessage;
import com.flowershop.authservice.dto.auth.LoginRequest;
import com.flowershop.authservice.dto.auth.LoginResponseDto;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.entity.AuthProvider;
import com.flowershop.authservice.entity.Role;
import com.flowershop.authservice.exceptions.BadCredentialsException;
import com.flowershop.authservice.exceptions.EmailAlreadyExistsException;
import com.flowershop.authservice.exceptions.InvalidGoogleTokenException;
import com.flowershop.authservice.exceptions.NotFoundException;
import com.flowershop.authservice.repository.UserRepository;
import com.flowershop.authservice.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GoogleAuthService googleAuthService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                String.format("Email '%s' is already registered",
                    request.getEmail()));
        }

        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .authProvider(AuthProvider.LOCAL)
            .role(Role.ROLE_USER)
            .isMarketingAllowed(request.getIsMarketingAllow())
            .build();
        userRepository.save(user);
        return new AuthResponse(jwtUtils.generateToken(user.getEmail(), user.getRole().name()));
    }

    public LoginResponseDto login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new NotFoundException(
                ApiErrorMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(request.getEmail())));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(ApiErrorMessage.INVALID_PASSWORD.getMessage(request.getEmail()));
        }

        String jwtToken = jwtUtils.generateToken(request.getEmail(), user.getRole().name());
        return new LoginResponseDto(jwtToken, user.getRole().name());

    }

    @Transactional
    public AuthResponse loginOrRegisterWithGoogle(String credential) {
        GoogleLoginOrRegisterDTO request = googleAuthService.loginWithGoogle(credential);
        if (request == null) {
            throw new InvalidGoogleTokenException("Unauthorized");
        }
        Optional<User> user = userRepository.findByProviderIdAndEmail(request.providerId(), request.email());
        if (user.isPresent()) {
            return new AuthResponse(jwtUtils.generateToken(user.get().getEmail(), user.get().getRole().name()));
        }
        String fullName = Stream.of(request.firstName(), request.lastName())
            .filter(Objects::nonNull)
            .map(String::trim)
            .filter(name -> !name.isEmpty())
            .collect(Collectors.joining(" "));

        User newUser = User.builder()
            .fullName(fullName)
            .email(request.email())
            .providerId(request.providerId())
            .authProvider(AuthProvider.GOOGLE)
            .role(Role.ROLE_USER)
            .isMarketingAllowed(true)
            .build();
        userRepository.save(newUser);
        return new AuthResponse(jwtUtils.generateToken(newUser.getEmail(), newUser.getRole().name()));
    }
}
