package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.dto.ProductRecommendResponse;
import java.util.List;

public interface ProductRecommendService {
    List<ProductRecommendResponse> getRecommendations();
    List<ProductFilterResponse> getProductsByIds(List<Long> ids);

}
