package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;
import org.springframework.data.domain.Page;

/**
 * Service interface for catalog management and product search operations.
 */
public interface CatalogService {
    /**
     * Retrieves a paginated list of products based on search criteria and filters.
     *
     * @param request the filter and pagination criteria
     * @return a page of mapped product filter responses
     */
    Page<ProductFilterResponse> getSearchedProducts(ProductFilterRequest request);
}
