package com.flowershop.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductUpdateRequest {

    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price format is invalid (max 8 digits, 2 decimals)")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @Min(value = 1, message = "Stems count must be at least 1")
    private Integer stemsCount;

    @Min(value = 1, message = "Height must be greater than 0")
    private Integer height;

    @Min(value = 0, message = "Discount cannot be less than 0%")
    @Max(value = 100, message = "Discount cannot be more than 100%")
    private Integer discountPercent;

    private Boolean isAvailable;
    private Boolean isNew;
    private Boolean isPopular;
    private Boolean isSeasonOffer;
    private Boolean isRecommended;

    private Set<Long> flowerTypeIds;
    private Set<Long> colorIds;
    private Set<Long> occasionIds;

    @Positive(message = "Bouquet type ID must be positive")
    private Long bouquetTypeId;
}
