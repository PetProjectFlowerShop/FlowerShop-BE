package com.flowershop.productservice.service;

import com.flowershop.productservice.dto.ProductImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    List<ProductImageResponse> addImages(Long productId, List<MultipartFile> files);

    void deleteImage(Long imageId);

    void setMainImage(Long productId, Long imageId);

    List<ProductImageResponse> getProductImages(Long productId);
}
