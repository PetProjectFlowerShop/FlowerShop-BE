package com.flowershop.productservice.service;

import com.flowershop.productservice.constants.APIErrorMessage;
import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductImageResponse;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.dto.ProductUpdateRequest;
import com.flowershop.productservice.entity.*;
import com.flowershop.productservice.exceptions.NotFoundException;
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

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final ColorRepository colorRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final BouquetTypeRepository bouquetTypeRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        Color color = colorRepository.findById(request.getColorId()).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.COLOR_NOT_FOUND_BY_ID.getMessage(request.getColorId())));
        FlowerType flowerType = flowerTypeRepository.findById(request.getFlowerTypeId()).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.FLOWER_TYPE_NOT_FOUND_BY_ID.getMessage(request.getFlowerTypeId())));
        BouquetType bouquetType = bouquetTypeRepository.findById(request.getBouquetTypeId()).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.BOUQUET_TYPE_NOT_FOUND_BY_ID.getMessage(request.getBouquetTypeId())));
        Product product = productMapper.create(request,color,flowerType,bouquetType);
        product = productRepository.save(product);
        ProductResponse response = productMapper.convertProductToProductResponse(product);
        return response;

    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
            new NotFoundException(APIErrorMessage.PRODUCT_NOT_FOUND_BY_ID.getMessage(id)));
        ProductResponse response = productMapper.convertProductToProductResponse(product);

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
        if (!Objects.equals(product.getFlowerType().getId(), request.getFlowerTypeId())) {
            FlowerType flowerType = flowerTypeRepository.findById(request.getFlowerTypeId()).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.FLOWER_TYPE_NOT_FOUND_BY_ID.getMessage(id)));
            product.setFlowerType(flowerType);
        }
        if (!Objects.equals(product.getColor().getId(), request.getColorId())) {
            Color color = colorRepository.findById(request.getColorId()).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.COLOR_NOT_FOUND_BY_ID.getMessage(id)));
            product.setColor(color);
        }
        if (!Objects.equals(product.getBouquetType().getId(), request.getBouquetTypeId())) {
            BouquetType type = bouquetTypeRepository.findById(request.getBouquetTypeId()).orElseThrow(() ->
                new NotFoundException(APIErrorMessage.BOUQUET_TYPE_NOT_FOUND_BY_ID.getMessage(id)));
            product.setBouquetType(type);
        }
        ProductResponse response = productMapper.convertProductToProductResponse(product);

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
}
