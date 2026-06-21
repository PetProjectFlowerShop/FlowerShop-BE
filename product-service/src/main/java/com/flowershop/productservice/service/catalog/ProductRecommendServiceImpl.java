package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.dto.ProductRecommendResponse;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.ProductImage;
import com.flowershop.productservice.mapper.FilterMapper;
import com.flowershop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductRecommendServiceImpl implements ProductRecommendService {
    private final ProductRepository productRepository;
    private final FilterMapper filterMapper;

    @Override
    public List<ProductRecommendResponse> getRecommendations() {
        return productRepository.findRandomRecommended().stream()
            .map(this::mapProductToRecommendResponse)
            .collect(Collectors.toList());

    }
    @Override
    public List<ProductFilterResponse> getProductsByIds(List<Long> ids) {
        return productRepository.findAllByIdIn(ids).stream()
            .map(filterMapper::mapProductToResponse)
            .collect(Collectors.toList());
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
