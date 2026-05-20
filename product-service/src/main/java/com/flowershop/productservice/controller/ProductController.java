package com.flowershop.productservice.controller;

import com.flowershop.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flowers")
public class ProductController {
    private final ProductService productService;

}
