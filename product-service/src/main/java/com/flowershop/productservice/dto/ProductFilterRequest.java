package com.flowershop.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class ProductFilterRequest {
    private Integer page;
    private Integer size;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private Integer minHeight;
    private Integer maxHeight;
    private Integer minNumberOfStems;
    private Integer maxNumberOfStems;

    private Set<Long> flowerTypeId;
    private Set<Long> colorId;
    private Set<Long> bouquetTypeId;
    private Set<Long> occasionIds;

    private Boolean isPopular;

    private String sortBy;   // price, isPopular
    private String sortOrder;  // asc, desc

}
