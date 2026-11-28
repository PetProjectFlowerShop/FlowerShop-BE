package com.flowershop.authservice.service.user;

import com.flowershop.authservice.dto.user.UserRequest;
import com.flowershop.authservice.dto.user.UserResponseDto;
import com.flowershop.authservice.entity.User;
import com.flowershop.authservice.exceptions.NotFoundException;
import com.flowershop.authservice.exceptions.constrains.ApiErrorMessage;
import com.flowershop.authservice.mapper.UserMapper;
import com.flowershop.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDto updateUserProfile(String userEmail, UserRequest request) {
        User user = findUserByEmail(userEmail);

        if (request.getFullName() != null) {
            user.setFullName(request.getFullName().trim());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber().trim());
        }
        if (request.getDateOfBirth() != null) {
            user.setBirthDate(request.getDateOfBirth());
        }

        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserProfile(String userEmail) {
        User user = findUserByEmail(userEmail);
        return userMapper.toResponseDto(user);
    }

    private User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(userEmail)));
    }
}
