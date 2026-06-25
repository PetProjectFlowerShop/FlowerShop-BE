package com.flowershop.productservice.service;

import com.flowershop.productservice.constants.APIErrorMessage;
import com.flowershop.productservice.dto.*;
import com.flowershop.productservice.entity.*;
import com.flowershop.productservice.exceptions.NotFoundException;
import com.flowershop.productservice.mapper.ProductImageMapper;
import com.flowershop.productservice.mapper.ProductMapper;
import com.flowershop.productservice.repository.BouquetTypeRepository;
import com.flowershop.productservice.repository.ColorRepository;
import com.flowershop.productservice.repository.FlowerTypeRepository;
import com.flowershop.productservice.repository.ProductRepository;
import com.flowershop.productservice.service.image.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final ColorRepository colorRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final BouquetTypeRepository bouquetTypeRepository;
    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        Set<Color> colors = request.getColorIds().stream()
            .map(id -> colorRepository.findById(id).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.COLOR_NOT_FOUND_BY_ID.getMessage(id))))
            .collect(Collectors.toSet());

        Set<FlowerType> flowerTypes = request.getFlowerTypeIds().stream()
            .map(id -> flowerTypeRepository.findById(id).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.FLOWER_TYPE_NOT_FOUND_BY_ID.getMessage(id))))
            .collect(Collectors.toSet());

        BouquetType bouquetType = bouquetTypeRepository.findById(request.getBouquetTypeId()).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.BOUQUET_TYPE_NOT_FOUND_BY_ID.getMessage(request.getBouquetTypeId())));
        Product product = productMapper.create(request);
        product.setBouquetType(bouquetType);
        product.setColors(colors);
        product.setFlowerTypes(flowerTypes);
        product = productRepository.save(product);
        ProductResponse response = productMapper.convertProductToProductResponse(product);
        response.setColors(convertColorsToColorDtos(product.getColors()));
        response.setFlowerTypes(convertFlowerTypesToFlowerTypeDtos(product.getFlowerTypes()));
        return response;

    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(id)));
        ProductResponse response = productMapper.convertProductToProductResponse(product);
        response.setColors(convertColorsToColorDtos(product.getColors()));
        response.setFlowerTypes(convertFlowerTypesToFlowerTypeDtos(product.getFlowerTypes()));
        response.setImages(product.getImages().stream()
            .map(productImageMapper::convert).toList());
        return response;
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(id)));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStemsCount(request.getStemsCount());
        product.setIsAvailable(request.getIsAvailable());
        product.setHeight(request.getHeight());
        product.setDiscountPercent(request.getDiscountPercent());
        product.setIsNew(request.getIsNew());
        product.setIsPopular(request.getIsPopular());
        product.setIsSeasonOffer(request.getIsSeasonOffer());
        product.setIsRecommended(request.getIsRecommended());
        Set<Color> colors = request.getColorIds().stream()
            .map(colorId -> colorRepository.findById(colorId).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.COLOR_NOT_FOUND_BY_ID.getMessage(colorId))))
            .collect(Collectors.toSet());
        Set<FlowerType> flowerTypes = request.getFlowerTypeIds().stream()
            .map(flowerTypeId -> flowerTypeRepository.findById(flowerTypeId).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.FLOWER_TYPE_NOT_FOUND_BY_ID.getMessage(flowerTypeId))))
            .collect(Collectors.toSet());
        product.setColors(colors);
        product.setFlowerTypes(flowerTypes);
        if (!Objects.equals(product.getBouquetType().getId(), request.getBouquetTypeId())) {
            BouquetType type = bouquetTypeRepository.findById(request.getBouquetTypeId()).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.BOUQUET_TYPE_NOT_FOUND_BY_ID.getMessage(id)));
            product.setBouquetType(type);
        }
        ProductResponse response = productMapper.convertProductToProductResponse(product);
        response.setColors(convertColorsToColorDtos(product.getColors()));
        response.setFlowerTypes(convertFlowerTypesToFlowerTypeDtos(product.getFlowerTypes()));
        response.setImages(product.getImages().stream()
            .map(productImageMapper::convert).toList());
        return response;
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(id)));
        List<ProductImageResponse> images = productImageService.getProductImages(id);
        for (ProductImageResponse image : images) {
            productImageService.deleteImage(image.getId());
        }
        productRepository.delete(product);

    }
    private Set<ColorDto> convertColorsToColorDtos(Set<Color>colors){
        return colors.stream()
            .map(c->new ColorDto(c.getId(),c.getName()))
            .collect(Collectors.toSet());

    }
    private Set<FlowerTypeDto>convertFlowerTypesToFlowerTypeDtos(Set<FlowerType>flowerTypes){
        return flowerTypes.stream()
            .map(f->new FlowerTypeDto(f.getId(),f.getName()))
            .collect(Collectors.toSet());
    }
}
