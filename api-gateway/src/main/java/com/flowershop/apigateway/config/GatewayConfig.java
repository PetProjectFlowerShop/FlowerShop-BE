package com.flowershop.apigateway.config;

import com.flowershop.apigateway.filters.AccessFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final AccessFilter accessFilter;

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authservice", r -> r.path("/auth/**")
                        .uri("lb://AUTHSERVICE"))
                .route("catalogservice", r -> r.path("/flowers/**")
                        .filters(f -> f.filter(accessFilter))
                        .uri("lb://CATALOGSERVICE"))
                .route("orderservice", r -> r.path("orders/**")
                        .filters(f -> f.filter(accessFilter))
                        .uri("lb://ORDERSERVICE"))
                .build();
    }
}
