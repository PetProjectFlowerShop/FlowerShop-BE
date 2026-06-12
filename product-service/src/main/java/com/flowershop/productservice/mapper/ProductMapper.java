package com.flowershop.productservice.mapper;

import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.entity.BouquetType;
import com.flowershop.productservice.entity.Color;
import com.flowershop.productservice.entity.FlowerType;
import com.flowershop.productservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product create(ProductCreateRequest productCreateRequest, Color color, FlowerType flowerType, BouquetType bouquetType);


    ProductResponse convertProductToProductResponse(Product product);
}
