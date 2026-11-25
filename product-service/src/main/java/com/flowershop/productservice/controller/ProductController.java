package com.flowershop.productservice.controller;
import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.dto.ProductUpdateRequest;
import com.flowershop.productservice.dto.orderservice.BouquetItemRequest;
import com.flowershop.productservice.dto.orderservice.BouquetItemResp;
import com.flowershop.productservice.service.product.ProductInventoryService;
import com.flowershop.productservice.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flowers")
public class ProductController {

    private final ProductService productService;
    private final ProductInventoryService productInventoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(
        @RequestBody @Valid ProductCreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(
        @PathVariable Long id,
        @RequestBody @Valid ProductUpdateRequest request) {

        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/get-product-price")
    public List<BouquetItemResp> getBouquetsPrices(@RequestBody List<BouquetItemRequest> items) {
        return productInventoryService.getProductsPrices(items);
    }
}
