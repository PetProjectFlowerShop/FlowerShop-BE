package com.flowershop.productservice.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean isNew;
    private Boolean isPopular;
    private Boolean isSeasonOffer;
    private String imageUrl;
    private Integer height;
    private Integer stemsCount;
    private Set<ColorDto> colors;
    private Set<OccasionDto> occasions;
    private Set<FlowerTypeDto> flowerTypes;
    private Long bouquetTypeId;
}
