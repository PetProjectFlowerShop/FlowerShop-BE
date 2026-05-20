package com.flowershop.apigateway.config;

import com.flowershop.apigateway.filters.AccessFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final AccessFilter accessFilter;
    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Value("${product.service.url}")
    private String catalogServiceUrl;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authservice", r -> r.path("/auth/**")
                        .uri(authServiceUrl))
                .route("productservice", r -> r.path("/flowers/**")
                        .filters(f -> f.filter(accessFilter))
                        .uri(catalogServiceUrl))
                .route("orderservice", r -> r.path("orders/**")
                        .filters(f -> f.filter(accessFilter))
                        .uri(orderServiceUrl))
                .build();
    }
}
