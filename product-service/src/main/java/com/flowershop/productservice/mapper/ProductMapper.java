package com.flowershop.productservice.mapper;
import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    imports = {Object.class}
)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "discountPercent", ignore = true)
    @Mapping(target = "isNew", ignore = true)
    @Mapping(target = "isPopular", ignore = true)
    @Mapping(target = "isSeasonOffer", ignore = true)
    @Mapping(target = "isRecommended", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "flowerTypes", ignore = true)
    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "bouquetType", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "occasions", ignore = true)
    Product create(ProductCreateRequest productCreateRequest);
    @Mapping(target = "flowerTypes", ignore = true)
    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductResponse convertProductToProductResponse(Product product);
}
