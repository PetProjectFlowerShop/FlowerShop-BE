package com.flowershop.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        // TODO: add routes to services using Eureka (registry-service) name uri
        return null;
    }
}
