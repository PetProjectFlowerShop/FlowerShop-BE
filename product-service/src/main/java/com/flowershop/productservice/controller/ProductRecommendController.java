package com.flowershop.productservice.controller;

import com.flowershop.productservice.dto.ProductRecommendResponse;
import com.flowershop.productservice.service.ProductRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flowers")
@RequiredArgsConstructor
public class ProductRecommendController {
    private final ProductRecommendService productRecommendService;

    @GetMapping("/recommendations")
    public List<ProductRecommendResponse> getRecommendations() {
        return productRecommendService.getRecommendations();
    }
}
