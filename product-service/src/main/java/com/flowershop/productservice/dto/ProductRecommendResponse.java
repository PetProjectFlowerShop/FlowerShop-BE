package com.flowershop.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecommendResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Boolean isNew;
    private Boolean isPopular;
    private Boolean isSeasonOffer;
    private Integer discountPercent;
    private Boolean isRecommended;
}
