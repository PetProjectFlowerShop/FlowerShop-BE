package com.flowershop.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotBlank(message = "Name is to be present")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    @NotBlank(message = "Description is to be present")
    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price format is invalid (max 8 digits, 2 decimals)")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "StemsCount is required")
    @Min(value = 1, message = "Stems count must be at least 1")
    private Integer stemsCount;

    @NotNull(message = "Height is required")
    @Min(value = 1, message = "Height must be greater than 0")
    private Integer height;

    @NotNull(message = "DiscountPercent is required")
    @Min(value = 0, message = "Discount cannot be less than 0%")
    @Max(value = 100, message = "Discount cannot be more than 100%")
    private Integer discountPercent;

    @NotNull(message = "isAvailable is required")
    private Boolean isAvailable;
    @NotNull(message = "isNew is required")
    private Boolean isNew;
    @NotNull(message = "isPopular is required")
    private Boolean isPopular;
    @NotNull(message = "isSeasonOffer is required")
    private Boolean isSeasonOffer;
    @NotNull(message = "isRecommended is required")
    private Boolean isRecommended;

    @NotEmpty(message = "FlowerTypeIds cannot be empty")
    private Set<Long> flowerTypeIds;

    @NotEmpty(message = "ColorIds cannot be empty")
    private Set<Long> colorIds;

    @NotEmpty(message = "OccasionIds cannot be empty")
    private Set<Long> occasionIds;

    @NotNull(message = "BouquetTypeId is required")
    @Positive(message = "Bouquet type ID must be positive")
    private Long bouquetTypeId;
}
