package com.flowershop.productservice.service;

import com.flowershop.productservice.dto.ProductRecommendResponse;
import java.util.List;

public interface ProductRecommendService {
    List<ProductRecommendResponse> getRecommendations();
}
