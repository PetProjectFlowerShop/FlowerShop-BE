package com.flowershop.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
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
    private Set<Long> flowerTypeIds;
    private Set<Long> colorIds;
    private Long bouquetTypeId;
}

