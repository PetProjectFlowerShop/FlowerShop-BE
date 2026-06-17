package com.flowershop.productservice.service;

import com.flowershop.productservice.dto.ProductRecommendResponse;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.ProductImage;
import com.flowershop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRecommendServiceImpl implements ProductRecommendService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductRecommendResponse> getRecommendations() {
        return productRepository.findRandomRecommended().stream()
            .map(this::mapProductToRecommendResponse)
            .collect(java.util.stream.Collectors.toList());

    }

    private ProductRecommendResponse mapProductToRecommendResponse(Product product) {
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
