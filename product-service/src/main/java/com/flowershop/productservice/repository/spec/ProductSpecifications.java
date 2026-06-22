package com.flowershop.productservice.repository.spec;

import com.flowershop.productservice.entity.Color;
import com.flowershop.productservice.entity.FlowerType;
import com.flowershop.productservice.entity.Occasion;
import com.flowershop.productservice.entity.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;
import java.util.Set;

public class ProductSpecifications {

    public static Specification<Product> minPrice(BigDecimal minPrice) {
        return (root, query, cb) ->
            cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(BigDecimal maxPrice) {
        return (root, query, cb) ->
            cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> minHeight(Integer minHeight) {
        return (root, query, cb) ->
            cb.greaterThanOrEqualTo(root.get("height"), minHeight);
    }

    public static Specification<Product> maxHeight(Integer maxHeight) {
        return (root, query, cb) ->
            cb.lessThanOrEqualTo(root.get("height"), maxHeight);
    }

    public static Specification<Product> minNumberOfStems(Integer minNumberOfStems) {
        return (root, query, cb) ->
            cb.greaterThanOrEqualTo(root.get("stemsCount"), minNumberOfStems);
    }

    public static Specification<Product> maxNumberOfStems(Integer maxNumberOfStems) {
        return (root, query, cb) ->
            cb.lessThanOrEqualTo(root.get("stemsCount"), maxNumberOfStems);
    }

    public static Specification<Product> flowerTypesIn(Set<Long> ids) {
        return (root, query, cb) -> {
            Join<Product, FlowerType> join = root.join("flowerTypes");
            query.distinct(true);
            return join.get("id").in(ids);
        };
    }

    public static Specification<Product> colorsIn(Set<Long> ids) {
        return (root, query, cb) -> {
            Join<Product, Color> join = root.join("colors");
            query.distinct(true);
            return join.get("id").in(ids);
        };
    }

    public static Specification<Product> bouquetTypeIn(Set<Long> ids) {
        return (root, query, cb) ->
            root.get("bouquetType").get("id").in(ids);
    }

    public static Specification<Product> occasionsIn(Set<Long> ids) {
        return (root, query, cb) -> {
            Join<Product, Occasion> join = root.join("occasions");
            query.distinct(true);
            return join.get("id").in(ids);
        };
    }

    public static Specification<Product> isAvailable() {
        return (root, query, cb)
            -> cb.isTrue(root.get("isAvailable"));
    }

    public static Specification<Product> search(String keyword) {

        return (root, query, cb) -> {
            String pattern = "%" + keyword.toLowerCase() + "%";
            return cb.or(cb.like(cb.lower(root.get("name")), pattern),
                cb.like(cb.lower(root.get("description")), pattern),
                cb.greaterThan(cb.function("similarity", Double.class, root.get("name"), cb.literal(keyword)), 0.3),
                cb.greaterThan(cb.function("similarity", Double.class, root.get("description"), cb.literal(keyword)), 0.3));
        };

    }
}
