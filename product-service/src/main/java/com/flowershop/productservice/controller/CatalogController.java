package com.flowershop.productservice.controller;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.service.catalog.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flowers/search")
public class CatalogController {
    private final CatalogService service;
    @GetMapping
    public List<ProductFilterResponse> getAllNeeded(@RequestBody ProductFilterRequest request) {
        return service.getSearchedProducts(request);
    }
}
