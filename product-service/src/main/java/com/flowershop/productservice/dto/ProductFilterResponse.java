package com.flowershop.productservice.dto;

import com.flowershop.productservice.entity.ProductImage;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
    private List<ProductImage> images;
}
