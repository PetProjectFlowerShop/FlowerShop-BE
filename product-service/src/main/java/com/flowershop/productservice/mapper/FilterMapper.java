package com.flowershop.productservice.mapper;

import com.flowershop.productservice.dto.BouquetTypeDto;
import com.flowershop.productservice.dto.ColorDto;
import com.flowershop.productservice.dto.FlowerTypeDto;
import com.flowershop.productservice.dto.OccasionDto;
import com.flowershop.productservice.dto.PackagingTypeDto;
import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.entity.BouquetType;
import com.flowershop.productservice.entity.Color;
import com.flowershop.productservice.entity.FlowerType;
import com.flowershop.productservice.entity.Occasion;
import com.flowershop.productservice.entity.PackagingType;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.ProductImage;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilterMapper {
    public ColorDto mapToColorDto(Color color) {
        return new ColorDto(color.getId(), color.getName());
    }

    public FlowerTypeDto mapToFlowerTypeDto(FlowerType flowerType) {
        return new FlowerTypeDto(flowerType.getId(), flowerType.getName());
    }

    public PackagingTypeDto mapToPackagingTypeDto(PackagingType packagingType) {
        return new PackagingTypeDto(packagingType.getId(), packagingType.getName(), packagingType.getPrice());
    }

    public BouquetTypeDto mapToBouquetTypeDto(BouquetType bouquetType, Set<PackagingType> packagingTypes) {
        return new BouquetTypeDto(bouquetType.getId(), bouquetType.getName(),
            packagingTypes.stream().map(this::mapToPackagingTypeDto).collect(Collectors.toSet()));
    }

    public OccasionDto mapToOccasionDto(Occasion occasion) {
        return new OccasionDto(occasion.getId(), occasion.getName());
    }

    public ProductFilterResponse mapProductToResponse(Product product) {
        Optional<String> imageUrl = product.getImages().stream()
            .filter(ProductImage::getIsMain)
            .map(ProductImage::getImageUrl)
            .findFirst();
        return ProductFilterResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .isNew(product.getIsNew())
            .isPopular(product.getIsPopular())
            .isSeasonOffer(product.getIsSeasonOffer())
            .imageUrl(imageUrl.orElse(""))
            .height(product.getHeight())
            .stemsCount(product.getStemsCount())
            .colors(product.getColors().stream().map(this::mapToColorDto).collect(Collectors.toSet()))
            .occasions(product.getOccasions().stream().map(this::mapToOccasionDto).collect(Collectors.toSet()))
            .flowerTypes(product.getFlowerTypes().stream().map(this::mapToFlowerTypeDto).collect(Collectors.toSet()))
            .bouquetTypeId(product.getBouquetType().getId())
            .build();
    }
}
