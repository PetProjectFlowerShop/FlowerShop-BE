package com.flowershop.productservice.repository.spec;

import com.flowershop.productservice.dto.ProductFilterRequest;
import org.springframework.data.domain.Sort;

public class ProductSorter {
    public static Sort buildSort(ProductFilterRequest filter) {
        if (filter.getSortBy() == null) {
            return Sort.unsorted();
        }
        Sort.Direction direction = "desc".equalsIgnoreCase(filter.getSortOrder())
            ? Sort.Direction.DESC
            : Sort.Direction.ASC;
        return Sort.by(direction, filter.getSortBy());
    }
}
