package com.flowershop.productservice.mapper;

import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "Spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    imports = Object.class)

public interface ProductImageMapper {
   // @Mapping(target = "productId",source = "product.id")
    ProductImageResponse convert(ProductImage productImage);

}
