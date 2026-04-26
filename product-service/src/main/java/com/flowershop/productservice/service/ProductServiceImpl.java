package com.flowershop.productservice.service;

import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.dto.ProductUpdateRequest;
import com.flowershop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        return null;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
