package com.flowershop.authservice.service;

import com.flowershop.authservice.model.dto.user.request.LoginUserRequestDto;
import com.flowershop.authservice.model.dto.user.request.NewUserRequestDto;
import com.flowershop.authservice.model.dto.user.response.LoginResponseDto;
import com.flowershop.authservice.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User>findByEmail(String email);
    LoginResponseDto login(LoginUserRequestDto request);
}
