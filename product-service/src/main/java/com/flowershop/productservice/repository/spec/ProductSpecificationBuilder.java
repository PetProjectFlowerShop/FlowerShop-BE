package com.flowershop.productservice.repository.spec;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationBuilder {

    public static Specification<Product> build(ProductFilterRequest filter) {

        Specification<Product> spec = Specification.where(ProductSpecifications.isAvailable());

        if (filter.getMinPrice() != null) {
            spec = spec.and(ProductSpecifications.minPrice(filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            spec = spec.and(ProductSpecifications.maxPrice(filter.getMaxPrice()));
        }

        if (filter.getMinHeight() != null) {
            spec = spec.and(ProductSpecifications.minHeight(filter.getMinHeight()));
        }

        if (filter.getMaxHeight() != null) {
            spec = spec.and(ProductSpecifications.maxHeight(filter.getMaxHeight()));
        }

        if (filter.getMinNumberOfStems() != null) {
            spec = spec.and(ProductSpecifications.minNumberOfStems(filter.getMinNumberOfStems()));
        }

        if (filter.getMaxNumberOfStems() != null) {
            spec = spec.and(ProductSpecifications.maxNumberOfStems(filter.getMaxNumberOfStems()));
        }

        if (filter.getFlowerTypeId() != null && !filter.getFlowerTypeId().isEmpty()) {
            spec = spec.and(ProductSpecifications.flowerTypesIn(filter.getFlowerTypeId()));
        }

        if (filter.getColorId() != null && !filter.getColorId().isEmpty()) {
            spec = spec.and(ProductSpecifications.colorsIn(filter.getColorId()));
        }

        if (filter.getBouquetTypeId() != null && !filter.getBouquetTypeId().isEmpty()) {
            spec = spec.and(ProductSpecifications.bouquetTypeIn(filter.getBouquetTypeId()));
        }

        if (filter.getOccasionIds() != null && !filter.getOccasionIds().isEmpty()) {
            spec = spec.and(ProductSpecifications.occasionsIn(filter.getOccasionIds()));
        }

        if (filter.getSortBy() != null) {

        }

        return spec;
    }
}
