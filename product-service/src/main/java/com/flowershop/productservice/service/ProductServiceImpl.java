package com.flowershop.productservice.service;

import com.flowershop.productservice.constants.APIErrorMessage;
import com.flowershop.productservice.dto.ProductCreateRequest;
import com.flowershop.productservice.dto.ProductResponse;
import com.flowershop.productservice.dto.ProductUpdateRequest;
import com.flowershop.productservice.entity.BouquetType;
import com.flowershop.productservice.entity.Color;
import com.flowershop.productservice.entity.FlowerType;
import com.flowershop.productservice.exceptions.NotFoundException;
import com.flowershop.productservice.repository.BouquetTypeRepository;
import com.flowershop.productservice.repository.ColorRepository;
import com.flowershop.productservice.repository.FlowerTypeRepository;
import com.flowershop.productservice.repository.ProductRepository;
import com.flowershop.productservice.service.image.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageService productImageService;
    private final ColorRepository colorRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final BouquetTypeRepository bouquetTypeRepository;

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {
        Color color = colorRepository.findById(request.getColorId()).orElseThrow(()->
            new NotFoundException(APIErrorMessage.COLOR_NOT_FOUND_BY_ID.getMessage(request.getColorId())));
        FlowerType flowerType=flowerTypeRepository.findById(request.getFlowerTypeId()).orElseThrow(()->
            new NotFoundException(APIErrorMessage.FLOWER_TYPE_NOT_FOUND_BY_ID.getMessage(request.getFlowerTypeId())));
        BouquetType bouquetType=bouquetTypeRepository.findById(request.getBouquetTypeId()).orElseThrow(()->
            new NotFoundException(APIErrorMessage.BOUQUET_TYPE_NOT_FOUND_BY_ID.getMessage(request.getBouquetTypeId())));

        return null;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
