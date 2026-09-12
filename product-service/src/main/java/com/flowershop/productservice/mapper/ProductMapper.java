package com.flowershop.productservice.mapper;
import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.dto.ProductUpdateRequest;
import com.flowershop.productservice.entity.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    builder = @Builder(disableBuilder = true),
    imports = {Object.class}
)
public interface ProductMapper {
    @Mapping(target = "quantity", source = "quantity")
    Product create(ProductCreateRequest productCreateRequest);

    @Mapping(target = "flowerTypes", ignore = true)
    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductResponse convertProductToProductResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "flowerTypes", ignore = true)
    @Mapping(target = "occasions", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "bouquetType", ignore = true)
    void updateProductFromRequest(ProductUpdateRequest request, @MappingTarget Product product);
}

