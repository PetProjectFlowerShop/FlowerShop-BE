package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.dto.ProductRecommendResponse;
import com.flowershop.productservice.mapper.FilterMapper;
import com.flowershop.productservice.mapper.ProductRecommendMapper;
import com.flowershop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductRecommendServiceImpl implements ProductRecommendService {
    private final ProductRepository productRepository;
    private final FilterMapper filterMapper;
    private final ProductRecommendMapper productRecommendMapper;

    @Override
    public List<ProductRecommendResponse> getRecommendations() {
        List<Long> ids = productRepository.findRandomRecommendedIds();

        return productRepository.findAllByIds(ids).stream()
            .map(productRecommendMapper::mapProductToRecommendResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductFilterResponse> getProductsByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty() || ids.stream().allMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Parameter 'ids' must contain at least one valid ID");
        }
        return productRepository.findAllByIdIn(ids).stream()
            .map(filterMapper::mapProductToResponse)
            .collect(Collectors.toList());
    }
}
