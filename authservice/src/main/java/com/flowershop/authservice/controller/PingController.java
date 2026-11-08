package com.flowershop.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/auth/ping")
    public String ping() {
        return "Auth Service is up and running!";
    }
}
