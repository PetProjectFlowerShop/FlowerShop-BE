package com.flowershop.productservice.service;

import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.dto.ProductUpdateRequest;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest request);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductUpdateRequest request);

    void deleteProduct(Long id);
}
