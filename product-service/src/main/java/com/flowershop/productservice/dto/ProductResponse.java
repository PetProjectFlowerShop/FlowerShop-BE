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
import java.util.Set;


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

    private Set<FlowerTypeDto> flowerTypes;
    private Set<ColorDto> colors;
    private BouquetType bouquetType;

    private List<ProductImageResponse> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getPopular() {
        return isPopular;
    }

    public void setPopular(Boolean popular) {
        isPopular = popular;
    }

    public Boolean getSeasonOffer() {
        return isSeasonOffer;
    }

    public void setSeasonOffer(Boolean seasonOffer) {
        isSeasonOffer = seasonOffer;
    }

    public Boolean getRecommended() {
        return isRecommended;
    }

    public void setRecommended(Boolean recommended) {
        isRecommended = recommended;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<FlowerTypeDto> getFlowerTypes() {
        return flowerTypes;
    }

    public void setFlowerTypes(Set<FlowerTypeDto> flowerTypes) {
        this.flowerTypes = flowerTypes;
    }

    public Set<ColorDto> getColors() {
        return colors;
    }

    public void setColors(Set<ColorDto> colors) {
        this.colors = colors;
    }

    public BouquetType getBouquetType() {
        return bouquetType;
    }

    public void setBouquetType(BouquetType bouquetType) {
        this.bouquetType = bouquetType;
    }

    public List<ProductImageResponse> getImages() {
        return images;
    }

    public void setImages(List<ProductImageResponse> images) {
        this.images = images;
    }
}
