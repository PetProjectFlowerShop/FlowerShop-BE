package com.flowershop.productservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Integer stemsCount;
    private Boolean isAvailable;
    private Integer height;
    private Integer discountPercent ;
    private Boolean isNew ;
    private Boolean isPopular;
    private Boolean isSeasonOffer ;
    private Boolean isRecommended ;
    private Long flowerTypeId;
    private Long colorId;
    private Long bouquetTypeId;
}
