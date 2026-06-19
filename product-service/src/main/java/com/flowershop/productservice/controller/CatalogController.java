package com.flowershop.productservice.controller;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.dto.ProductRecommendResponse;
import com.flowershop.productservice.service.ProductRecommendService;
import com.flowershop.productservice.service.catalog.CatalogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flowers")
public class CatalogController {
    private final CatalogService service;
    private final ProductRecommendService productRecommendService;

    @GetMapping("/search")
    public Page<ProductFilterResponse> getAllNeeded(@Valid ProductFilterRequest request) {
        return service.getSearchedProducts(request);
    }

    @GetMapping("/batch")
    public List<ProductFilterResponse> getProductsByIds(@RequestParam List<Long> ids) {
        return service.getProductsByIds(ids);
    }

    @GetMapping("/recommendations")
    public List<ProductRecommendResponse> getRecommendations() {
        return productRecommendService.getRecommendations();
    }
}
