package com.flowershop.productservice.mapper.impl;

import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.entity.ProductImage;
import com.flowershop.productservice.mapper.ProductImageMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapperImpl implements ProductImageMapper {
    @Override
    public ProductImageResponse convert(ProductImage productImage) {
        ProductImageResponse productImageResponse = new ProductImageResponse();
        productImageResponse.setId(productImage.getId());
        productImageResponse.setImageUrl(productImage.getImageUrl());
        productImageResponse.setIsMain(productImage.getIsMain());
        productImageResponse.setProductId(productImage.getProduct().getId());
        return productImageResponse;

    }
}
