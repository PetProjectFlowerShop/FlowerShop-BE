package com.flowershop.productservice.service.image;

import com.flowershop.productservice.constants.APIErrorMessage;
import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.ProductImage;
import com.flowershop.productservice.exceptions.NotFoundException;
import com.flowershop.productservice.mapper.ProductImageMapper;
import com.flowershop.productservice.repository.ProductImageRepository;
import com.flowershop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;
    private final ProductImageMapper productImageMapper;

    @Override
    public List<ProductImageResponse> addImages(Long productId, List<MultipartFile> files) throws IOException {
        List<ProductImageResponse> productImageResponses = new ArrayList<>();
        Product product = productRepository.findById(productId).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(productId)));
        for (MultipartFile file : files) {
            String url = fileStorageService.uploadFile(file);
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setImageUrl(url);
            productImage = productImageRepository.save(productImage);
            ProductImageResponse response = productImageMapper.convert(productImage);
            productImageResponses.add(response);
        }
        return productImageResponses;
    }

    @Override
    public void deleteImage(Long imageId) {
        ProductImage productImage = productImageRepository.findById(imageId).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_IMAGE_NOT_FOUND_BY_ID.getMessage(imageId)));
        fileStorageService.deleteFile(productImage.getImageUrl());
        productImageRepository.delete(productImage);
    }

    @Override
    public void setMainImage(Long productId, Long imageId) {
        Optional<ProductImage> productImageOptional = productImageRepository.findByProductIdAndIsMainTrue(productId);
        if (productImageOptional.isPresent()) {
            ProductImage productImage = productImageOptional.get();
            if (!Objects.equals(productImage.getProduct().getId(), productId)) {
                productImage.setIsMain(false);
                productImageRepository.save(productImage);
                productImage = productImageRepository.findById(imageId).orElseThrow(() ->
                    new NotFoundException(APIErrorMessage.PRODUCT_IMAGE_NOT_FOUND_BY_ID.getMessage(imageId)));
                productImage.setIsMain(true);
                productImageRepository.save(productImage);
            }
        }

    }

    @Override
    public List<ProductImageResponse> getProductImages(Long productId) {
        return productImageRepository.findAllByProductId(productId).stream().map(productImageMapper::convert).toList();

    }
}
