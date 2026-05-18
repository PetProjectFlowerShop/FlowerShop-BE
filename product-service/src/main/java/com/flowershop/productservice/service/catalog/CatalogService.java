package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;

import java.util.List;

public interface CatalogService {
    List<ProductFilterResponse> getSearchedProducts(ProductFilterRequest request);
}
