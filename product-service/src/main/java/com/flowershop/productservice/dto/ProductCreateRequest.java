package com.flowershop.productservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class ProductCreateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Integer stems_count;
    private Integer height;
    private Long flowerTypeId;
    private Long colorId;
    private Long bouquetTypeId;
}
