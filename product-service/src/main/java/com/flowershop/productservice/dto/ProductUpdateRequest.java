package com.flowershop.productservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest implements Serializable {

    private Long id;
    @NotBlank(message = "Name is to be present")
    private String name;
    @NotBlank(message = "Description is to present")
    private String description;
    @Min(value = 0,message = "Min price is 0")
    private BigDecimal price;
    @Min(value =0,message = "Min quantity is 0")
    private Integer quantity;
    @Min(value =0,message = "Min stemsCount is 0")
    private Integer stemsCount;
    @NotNull(message = "is available must to present")
    private Boolean isAvailable;
    @Min(value =0,message = "Min height is 0")
    private Integer height;
    @Min(value = 0, message = "Discount percent cannot be less than 0")
    @Max(value = 100, message = "Discount percent cannot be more than 100")
    private Integer discountPercent ;
    @NotNull(message = "is new must to present")
    private Boolean isNew ;
    @NotNull(message = "is popular must to present")
    private Boolean isPopular;
    @NotNull(message = "is Season Offer must to present")
    private Boolean isSeasonOffer ;
    @NotNull(message = "is Recommended Offer must to present")
    private Boolean isRecommended ;
    private Set<Long> flowerTypeIds;
    private Set<Long> colorIds;
    @Min(value = 1,message = "BouquetTypeId must be present")
    private Long bouquetTypeId;
}
