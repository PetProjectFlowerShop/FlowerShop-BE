package com.flowershop.productservice.mapper;

import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.entity.BouquetType;
import com.flowershop.productservice.entity.Color;
import com.flowershop.productservice.entity.FlowerType;
import com.flowershop.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "Spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    imports = Object.class)
public interface ProductMapper {
    @Mapping(target = "color", source = "color")
    @Mapping(target = "flowerType",source = "flowerType")
    @Mapping(target = "bouquetType",source =" bouquetType")
    Product create(ProductCreateRequest productCreateRequest, Color color, FlowerType flowerType, BouquetType bouquetType);


    ProductResponse convertProductToProductResponse(Product product);

}
