package com.flowershop.productservice.service.image;

import com.flowershop.productservice.constants.APIErrorMessage;
import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.ProductImage;
import com.flowershop.productservice.exception.exceptions.NotFoundException;
import com.flowershop.productservice.mapper.ProductImageMapper;
import com.flowershop.productservice.repository.ProductImageRepository;
import com.flowershop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;
    private final ProductImageMapper productImageMapper;

    @Override
    public List<ProductImageResponse> getProductImages(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(productId));
        }
        return productImageRepository.findAllByProductId(productId).stream().map(productImageMapper::convert).toList();

    }

    @Transactional()
    @Override
    public List<ProductImageResponse> addImages(Long productId, MultipartFile[] files) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(productId)));

        List<ProductImage> newImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = fileStorageService.uploadFile(file);
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setImageUrl(url);

            newImages.add(productImage);
        }
        List<ProductImage> savedImages = productImageRepository.saveAll(newImages);
        return savedImages.stream()
            .map(productImageMapper::convert)
            .toList();
    }

    @Transactional()
    @Override
    public ProductImageResponse setMainImage(Long productId, Long imageId) {
        ProductImage newProductImage = productImageRepository.findById(imageId)
            .orElseThrow(() -> new NotFoundException(APIErrorMessage.PRODUCT_IMAGE_NOT_FOUND_BY_ID.getMessage(productId)));

        if (!newProductImage.getProduct().getId().equals(productId)) {
            throw new IllegalArgumentException(APIErrorMessage.IMAGE_NOT_BELONG_TO_PRODUCT.getMessage(imageId, productId));
        }
        if (Boolean.TRUE.equals(newProductImage.getIsMain())) {
            return productImageMapper.convert(newProductImage);
        }

        productImageRepository.findByProductIdAndIsMainTrue(productId)
            .ifPresent(pi -> pi.setIsMain(false));
        //Dirty Checking
        newProductImage.setIsMain(true);

        return productImageMapper.convert(newProductImage);
    }

    @Transactional()
    @Override
    public void deleteImage(Long imageId) {
        ProductImage productImage = productImageRepository.findById(imageId).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_IMAGE_NOT_FOUND_BY_ID.getMessage(imageId)));
        productImageRepository.delete(productImage);
        fileStorageService.deleteFile(productImage.getImageUrl());
    }

    @Transactional()
    @Override
    public void deleteAllProductImages(Long productId) {
        validateProductExists(productId);

        List<String> imageUrls = productImageRepository.findAllImageUrlsByProductId(productId);

        if (imageUrls.isEmpty()) {
            return;
        }

        productImageRepository.deleteAllByProductId(productId);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                imageUrls.forEach(fileStorageService::deleteFile);
            }
        });
    }

    private void validateProductExists(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(productId));
        }
    }

}
