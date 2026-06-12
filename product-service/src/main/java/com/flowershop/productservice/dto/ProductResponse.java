package com.flowershop.productservice.dto;

import com.flowershop.productservice.entity.BouquetType;
import com.flowershop.productservice.entity.Color;
import com.flowershop.productservice.entity.FlowerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private FlowerType flowerType;
    private Color color;
    private BouquetType bouquetType;

    private List<ProductImageResponse> images;
}
