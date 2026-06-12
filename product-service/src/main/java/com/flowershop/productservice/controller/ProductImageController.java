package com.flowershop.productservice.controller;

import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.service.ProductService;
import com.flowershop.productservice.service.image.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ProductImageController {
    private final ProductImageService productImageService;
    private final ProductService productService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Void> addImages(@PathVariable("id") long id,
                                          @RequestParam(name = "images") MultipartFile[] images) throws IOException {
        productImageService.addImages(id, images);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        productImageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/main/{productId}")
    public ResponseEntity<Void> setMainImage(@PathVariable("productId") long productId,
                                             @RequestParam(name = "imageId") long imageId) {
        productImageService.setMainImage(productId, imageId);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getProductById(id));
    }

}
