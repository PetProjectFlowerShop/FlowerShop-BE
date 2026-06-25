package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterOptionsResponse;

public interface FilterOptionsService {
    /**
     * Retrieves all available filter options (e.g., flower types, colors, occasions)
     * used to populate catalog filters on the frontend.
     *
     * @return response containing lists of all filter categories
     */
    ProductFilterOptionsResponse getFilterOptions();
}
