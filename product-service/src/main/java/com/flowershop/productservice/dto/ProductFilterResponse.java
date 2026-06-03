package com.flowershop.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

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
    private Long colorId;
    private Long bouquetTypeId;
}
