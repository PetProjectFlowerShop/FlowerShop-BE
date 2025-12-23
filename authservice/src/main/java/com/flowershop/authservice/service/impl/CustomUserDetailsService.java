package com.flowershop.authservice.service.impl;

import com.flowershop.authservice.model.constrains.ApiErrorMessage;
import com.flowershop.authservice.model.entity.User;
import com.flowershop.authservice.model.exceptions.NotFoundException;
import com.flowershop.authservice.security.UsersDetails;
import com.flowershop.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_EMAIL.getMessage(email)));
        return new UsersDetails(user);
    }
}
