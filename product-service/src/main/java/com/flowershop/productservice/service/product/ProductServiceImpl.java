package com.flowershop.productservice.service.product;

import com.flowershop.productservice.constants.APIErrorMessage;
import com.flowershop.productservice.dto.*;
import com.flowershop.productservice.entity.*;
import com.flowershop.productservice.exceptions.NotFoundException;
import com.flowershop.productservice.mapper.ProductImageMapper;
import com.flowershop.productservice.mapper.ProductMapper;
import com.flowershop.productservice.repository.BouquetTypeRepository;
import com.flowershop.productservice.repository.ColorRepository;
import com.flowershop.productservice.repository.FlowerTypeRepository;
import com.flowershop.productservice.repository.OccasionRepository;
import com.flowershop.productservice.repository.ProductRepository;
import com.flowershop.productservice.service.image.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
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
    private final OccasionRepository occasionRepository;
    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        Set<Color> colors = fetchColors(request.getColorIds());
        Set<FlowerType> flowerTypes = fetchFlowerTypes(request.getFlowerTypeIds());
        Set<Occasion> occasions = fetchOccasions(request.getOccasionIds());
        BouquetType bouquetType = fetchBouquetType(request.getBouquetTypeId());

        Product product = productMapper.create(request);
        product.setBouquetType(bouquetType);
        product.setColors(colors);
        product.setFlowerTypes(flowerTypes);
        product.setOccasions(occasions);
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

        productMapper.updateProductFromRequest(request, product);

        if (request.getColorIds() != null) {
            product.setColors(fetchColors(request.getColorIds()));
        }
        if (request.getFlowerTypeIds() != null) {
            product.setFlowerTypes(fetchFlowerTypes(request.getFlowerTypeIds()));
        }
        if (request.getBouquetTypeId() != null) {
            product.setBouquetType(fetchBouquetType(request.getBouquetTypeId()));
        }
        if (request.getOccasionIds() != null) {
            product.setOccasions(fetchOccasions(request.getOccasionIds()));
        }

        product = productRepository.save(product);

        return productMapper.convertProductToProductResponse(product);
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

    private Set<ColorDto> convertColorsToColorDtos(Set<Color> colors) {
        return colors.stream()
            .map(c -> new ColorDto(c.getId(), c.getName()))
            .collect(Collectors.toSet());

    }

    private Set<FlowerTypeDto> convertFlowerTypesToFlowerTypeDtos(Set<FlowerType> flowerTypes) {
        return flowerTypes.stream()
            .map(f -> new FlowerTypeDto(f.getId(), f.getName()))
            .collect(Collectors.toSet());
    }

    private Set<Color> fetchColors(Set<Long> colorIds) {
        if (colorIds == null || colorIds.isEmpty()) {
            return Collections.emptySet();
        }
        return colorIds.stream()
            .map(id -> colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(APIErrorMessage.COLOR_NOT_FOUND_BY_ID.getMessage(id))))
            .collect(Collectors.toSet());
    }

    private Set<FlowerType> fetchFlowerTypes(Set<Long> flowerTypeIds) {
        if (flowerTypeIds == null || flowerTypeIds.isEmpty()) {
            return Collections.emptySet();
        }
        return flowerTypeIds.stream()
            .map(id -> flowerTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(APIErrorMessage.FLOWER_TYPE_NOT_FOUND_BY_ID.getMessage(id))))
            .collect(Collectors.toSet());
    }

    private Set<Occasion> fetchOccasions(Set<Long> occasionIds) {
        if (occasionIds == null || occasionIds.isEmpty()) {
            return Collections.emptySet();
        }
        return occasionIds.stream()
            .map(id -> occasionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(APIErrorMessage.OCCASION_NOT_FOUND_BY_ID.getMessage(id))))
            .collect(Collectors.toSet());
    }

    private BouquetType fetchBouquetType(Long bouquetTypeId) {
        return bouquetTypeRepository.findById(bouquetTypeId)
            .orElseThrow(() -> new NotFoundException(APIErrorMessage.BOUQUET_TYPE_NOT_FOUND_BY_ID.getMessage(bouquetTypeId)));
    }
}
