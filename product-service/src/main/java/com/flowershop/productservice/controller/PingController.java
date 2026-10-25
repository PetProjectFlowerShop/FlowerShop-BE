package com.flowershop.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/flowers/ping")
    public String ping() {
        return "Product server is up and running!";
    }
}
