package com.flowershop.productservice.controller;

import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.service.image.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flowers")
public class ProductImageController {
    private final ProductImageService productImageService;

    @GetMapping("/{productId}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductImageResponse> getProductImages(@PathVariable("productId") Long productId) {
        return productImageService.getProductImages(productId);
    }

    @PostMapping("/{productId}/images")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductImageResponse> addImages(
        @RequestParam(name = "images") MultipartFile[] images,
        @PathVariable("productId") Long productId) throws IOException {
        return productImageService.addImages(productId, images);

    }

    @DeleteMapping("/images/{imageId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("imageId") long imageId) {
        productImageService.deleteImage(imageId);
    }

    @PatchMapping("/{productId}/images/{imageId}/main")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setMainImage(@PathVariable("productId") Long productId,
                             @PathVariable("imageId") Long imageId) {
        productImageService.setMainImage(productId, imageId);
    }

}
