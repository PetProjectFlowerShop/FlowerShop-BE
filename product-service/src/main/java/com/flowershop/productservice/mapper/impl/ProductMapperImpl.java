package com.flowershop.productservice.mapper.impl;

import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.entity.BouquetType;
import com.flowershop.productservice.entity.Color;
import com.flowershop.productservice.entity.FlowerType;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.mapper.ProductMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product create(ProductCreateRequest productCreateRequest, Color color, FlowerType flowerType, BouquetType bouquetType) {
        Product product=new Product();
        product.setName(productCreateRequest.getName());
        product.setDescription(productCreateRequest.getDescription());
        product.setPrice(productCreateRequest.getPrice());
        product.setQuantity(productCreateRequest.getQuantity());
        product.setStemsCount(productCreateRequest.getStemsCount());
        product.setHeight(productCreateRequest.getHeight());
        product.setFlowerType(flowerType);
        product.setColor(color);
        product.setBouquetType(bouquetType);
        return product;
    }

    @Override
    public ProductResponse convertProductToProductResponse(Product product) {
        ProductResponse response =new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setStemsCount(product.getStemsCount());
        response.setIsAvailable(product.getIsAvailable());
        response.setHeight(product.getHeight());
        response.setDiscountPercent(product.getDiscountPercent());
        response.setIsNew(product.getIsNew());
        response.setIsPopular(product.getIsPopular());
        response.setIsSeasonOffer(product.getIsSeasonOffer());
        response.setIsRecommended(product.getIsRecommended());
        response.setFlowerType(product.getFlowerType());
        response.setColor(product.getColor());
        response.setBouquetType(product.getBouquetType());
        response.setCreatedAt(product.getCreatedAt());


        return response;
    }
}
