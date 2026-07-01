package com.flowershop.productservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
    @Min(value = 0, message = "Discount percent cannot be less than 0")
    @Max(value = 100, message = "Discount percent cannot be more than 100")
    private Integer discountPercent;
    @NotNull(message = "is new must to present")
    private Boolean isNew;
    @NotNull(message = "is popular must to present")
    private Boolean isPopular;
    @NotNull(message = "is Season Offer must to present")
    private Boolean isSeasonOffer;
    @NotNull(message = "is Recommended Offer must to present")
    private Boolean isRecommended;
    private Set<Long> flowerTypeIds;
    private Set<Long> colorIds;
    @Min(value = 1,message = "BouquetTypeId must be present")
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
}

