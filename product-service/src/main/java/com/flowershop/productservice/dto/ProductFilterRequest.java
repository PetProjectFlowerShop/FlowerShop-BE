package com.flowershop.productservice.dto;

import com.flowershop.productservice.dto.validation.ValidPriceRange;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@ValidPriceRange
public class ProductFilterRequest {
    @Min(0)
    private Integer page;
    @Min(1)
    @Max(50)
    private Integer size;

    @DecimalMin(value = "0.0", message = "minPrice must be >= 0")
    private BigDecimal minPrice;
    @DecimalMin(value = "0.0", message = "maxPrice must be >= 0")
    private BigDecimal maxPrice;

    @Min(value = 0, message = "minHeight must be >= 0")
    private Integer minHeight;
    @Min(value = 0, message = "maxHeight must be >= 0")
    private Integer maxHeight;
    @Min(value = 0, message = "min numberOfStems must be >= 0")
    private Integer minNumberOfStems;
    @Min(value = 0, message = "maxNumberOfStems must be >= 0")
    private Integer maxNumberOfStems;

    private Set<Long> flowerTypeId;
    private Set<Long> colorId;
    private Set<Long> bouquetTypeId;
    private Set<Long> occasionIds;

    private Boolean isPopular;

    @Pattern(regexp = "price|isPopular", message = "sortBy must be: price or isPopular")
    private String sortBy;
    private String sortOrder;
}
