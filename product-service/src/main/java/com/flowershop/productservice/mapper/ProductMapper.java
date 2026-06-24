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

    Product create(ProductCreateRequest productCreateRequest);
    @Mapping(target = "flowerTypes", ignore = true)
    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductResponse convertProductToProductResponse(Product product);

}
