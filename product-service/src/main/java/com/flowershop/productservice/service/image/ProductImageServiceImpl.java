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
    public List<ProductImageResponse> addImages(Long productId, MultipartFile[] files) throws IOException {
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

    @Transactional
    @Override
    public ProductImageResponse setMainImage(Long productId, Long imageId) {
        ProductImage newProductImage = productImageRepository.findById(imageId)
            .orElseThrow(() -> new NotFoundException(APIErrorMessage.PRODUCT_IMAGE_NOT_FOUND_BY_ID.getMessage(productId)));

        if (!newProductImage.getProduct().getId().equals(productId)) {
             throw new IllegalArgumentException(APIErrorMessage.IMAGE_NOT_BELONG_TO_PRODUCT.getMessage(imageId, productId));
        }
        if(Boolean.TRUE.equals(newProductImage.getIsMain())){
            return productImageMapper.convert(newProductImage);
        }

        productImageRepository.findByProductIdAndIsMainTrue(productId)
            .ifPresent(pi -> pi.setIsMain(false));
        //Dirty Checking
        newProductImage.setIsMain(true);

        return productImageMapper.convert(newProductImage);
    }

    @Override
    public List<ProductImageResponse> getProductImages(Long productId) {
        return productImageRepository.findAllByProductId(productId).stream().map(productImageMapper::convert).toList();

    }
}
