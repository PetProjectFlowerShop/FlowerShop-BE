package com.flowershop.productservice.service;

import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductImageService productImageService;

    @Override
    public List<ProductImageResponse> addImages(Long productId, List<MultipartFile> files) {
        return List.of();
    }

    @Override
    public void deleteImage(Long imageId) {
    }

    @Override
    public void setMainImage(Long productId, Long imageId) {

    }

    @Override
    public List<ProductImageResponse> getProductImages(Long productId) {
        return List.of();
    }
}
