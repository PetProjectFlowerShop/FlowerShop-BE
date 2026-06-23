package com.flowershop.productservice.mapper;

import com.flowershop.productservice.dto.ProductRecommendResponse;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRecommendMapper {

    public ProductRecommendResponse mapProductToRecommendResponse(Product product) {
        return ProductRecommendResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .imageUrl(product.getImages().stream()
                .filter(ProductImage::getIsMain)
                .map(ProductImage::getImageUrl)
                .findFirst().orElse(""))
            .price(product.getPrice())
            .isSeasonOffer(product.getIsSeasonOffer())
            .discountPercent(product.getDiscountPercent())
            .isPopular(product.getIsPopular())
            .isNew(product.getIsNew())
            .isRecommended(product.getIsRecommended())
            .build();
    }
}
