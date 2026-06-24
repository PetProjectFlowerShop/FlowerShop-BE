package com.flowershop.productservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "stems_count", nullable = false)
    private Integer stemsCount;

    @Column(name = "is_available")
    @Builder.Default
    private Boolean isAvailable = true;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "discount_percent")
    @Builder.Default
    private Integer discountPercent = 0;

    @Column(name = "is_new")
    @Builder.Default
    private Boolean isNew = false;

    @Column(name = "is_popular")
    @Builder.Default
    private Boolean isPopular = false;

    @Column(name = "is_season_offer")
    @Builder.Default
    private Boolean isSeasonOffer = false;

    @Column(name = "is_recommended")
    @Builder.Default
    private Boolean isRecommended = false;

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
        name = "product_flower_types",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "flower_type_id")
    )
    private Set<FlowerType> flowerTypes = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "product_colors",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> colors = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "bouquet_type_id")
    private BouquetType bouquetType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "product_occasions",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "occasion_id")
    )

    private Set<Occasion> occasions = new HashSet<>();

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

    public Set<FlowerType> getFlowerTypes() {
        return flowerTypes;
    }

    public void setFlowerTypes(Set<FlowerType> flowerTypes) {
        this.flowerTypes = flowerTypes;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public BouquetType getBouquetType() {
        return bouquetType;
    }

    public void setBouquetType(BouquetType bouquetType) {
        this.bouquetType = bouquetType;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Set<Occasion> getOccasions() {
        return occasions;
    }

    public void setOccasions(Set<Occasion> occasions) {
        this.occasions = occasions;
    }
}
