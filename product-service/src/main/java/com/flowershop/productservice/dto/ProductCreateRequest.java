package com.flowershop.productservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;


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
    private Set<Long> flowerTypeIds;
    private Set<Long> colorIds;
    private Long bouquetTypeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStemsCount() {
        return stemsCount;
    }

    public void setStemsCount(Integer stemsCount) {
        this.stemsCount = stemsCount;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Set<Long> getFlowerTypeIds() {
        return flowerTypeIds;
    }

    public void setFlowerTypeIds(Set<Long> flowerTypeIds) {
        this.flowerTypeIds = flowerTypeIds;
    }

    public Set<Long> getColorIds() {
        return colorIds;
    }

    public void setColorIds(Set<Long> colorIds) {
        this.colorIds = colorIds;
    }

    public Long getBouquetTypeId() {
        return bouquetTypeId;
    }

    public void setBouquetTypeId(Long bouquetTypeId) {
        this.bouquetTypeId = bouquetTypeId;
    }
}

