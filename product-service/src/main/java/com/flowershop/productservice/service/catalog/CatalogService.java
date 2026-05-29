package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;
import org.springframework.data.domain.Page;

public interface CatalogService {
    Page<ProductFilterResponse> getSearchedProducts(ProductFilterRequest request);
}
