package com.flowershop.productservice.mapper;

import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    ProductImageResponse convert(ProductImage productImage);
}
