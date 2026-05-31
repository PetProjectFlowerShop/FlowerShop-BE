package com.flowershop.productservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class ProductCreateRequest {
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
    @Min(value =0,message = "Min height is 0")
    private Integer height;
    private Long flowerTypeId;
    private Long colorId;
    private Long bouquetTypeId;
}
