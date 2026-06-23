package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.dto.ProductRecommendResponse;
import java.util.List;

public interface ProductRecommendService {

    /**
     * Retrieves a list of random (4) recommended products.
     * * @return a list of ProductRecommendResponse objects.
     */
    List<ProductRecommendResponse> getRecommendations();

    /**
     * Retrieves a list of products based on the provided list of IDs.
     * * @param ids the list of product IDs to fetch.
     */
    List<ProductFilterResponse> getProductsByIds(List<Long> ids);

}
