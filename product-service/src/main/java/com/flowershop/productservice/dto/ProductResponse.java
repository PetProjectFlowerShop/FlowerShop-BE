package com.flowershop.productservice.dto;

import com.flowershop.productservice.entity.BouquetType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Integer stemsCount;
    private Boolean isAvailable;
    private Integer height;
    private Integer discountPercent;
    private Boolean isNew;
    private Boolean isPopular;
    private Boolean isSeasonOffer;
    private Boolean isRecommended;
    private LocalDateTime createdAt;

    private Set<FlowerTypeDto> flowerTypes;
    private Set<ColorDto> colors;
    private BouquetType bouquetType;

    private List<ProductImageResponse> images;
}
