package com.flowershop.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
public class CorsConfig {
    @Bean
    CorsWebFilter corsFilter() {
        // TODO: add cors configuration
        return null;
    }
}
