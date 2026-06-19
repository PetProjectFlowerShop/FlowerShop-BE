package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CatalogService {
    Page<ProductFilterResponse> getSearchedProducts(ProductFilterRequest request);

    List<ProductFilterResponse> getProductsByIds(List<Long> ids);
}
