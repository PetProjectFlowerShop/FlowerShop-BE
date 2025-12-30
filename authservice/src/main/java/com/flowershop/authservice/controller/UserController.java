package com.flowershop.authservice.controller;

import com.flowershop.authservice.dto.LoginRequest;
import com.flowershop.authservice.dto.LoginResponseDto;
import com.flowershop.authservice.dto.MyResponse;
import com.flowershop.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<MyResponse<LoginResponseDto>> login(@RequestBody LoginRequest request) {
        LoginResponseDto loginResponseDto = userService.login(request);
        MyResponse<LoginResponseDto> myResponse = MyResponse.createSuccess(loginResponseDto);
        return ResponseEntity.ok(myResponse);
    }
}
