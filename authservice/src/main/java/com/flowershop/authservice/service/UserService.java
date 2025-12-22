package com.flowershop.authservice.service;

import com.flowershop.authservice.model.dto.user.NewUserRequestDto;
import com.flowershop.authservice.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User>findByEmail(String email);
    User register(NewUserRequestDto request);
}
