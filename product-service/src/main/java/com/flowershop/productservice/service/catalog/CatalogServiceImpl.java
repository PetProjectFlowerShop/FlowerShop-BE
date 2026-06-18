package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ColorDto;
import com.flowershop.productservice.dto.FlowerTypeDto;
import com.flowershop.productservice.dto.OccasionDto;
import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.ProductImage;
import com.flowershop.productservice.repository.ProductRepository;
import com.flowershop.productservice.repository.spec.ProductSorter;
import com.flowershop.productservice.repository.spec.ProductSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final ProductRepository productRepository;

    @Override
    public Page<ProductFilterResponse> getSearchedProducts(ProductFilterRequest request) {
        Pageable pageable = buildPage(request);
        Specification<Product> spec = ProductSpecificationBuilder.build(request);
        return productRepository.findAll(spec, pageable)
            .map(this::mapProductToResponse);
    }

    @Override
    public List<ProductFilterResponse> getProductsByIds(List<Long> ids) {
        return productRepository.findAllByIdIn(ids).stream()
            .map(this::mapProductToResponse)
            .collect(Collectors.toList());
    }

    private Pageable buildPage(ProductFilterRequest filter) {
        int page = filter.getPage() != null ? filter.getPage() : 0;
        int size = filter.getSize() != null ? filter.getSize() : 10;
        Sort sort = ProductSorter.buildSort(filter);
        return PageRequest.of(page, size, sort);
    }

    private ProductFilterResponse mapProductToResponse(Product product) {
        Optional<String> imageUrl = product.getImages().stream()
            .filter(ProductImage::getIsMain)
            .map(ProductImage::getImageUrl)
            .findFirst();
        return ProductFilterResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .isNew(product.getIsNew())
            .isPopular(product.getIsPopular())
            .isSeasonOffer(product.getIsSeasonOffer())
            .imageUrl(imageUrl.orElse(""))
            .height(product.getHeight())
            .stemsCount(product.getStemsCount())
            .colors(product.getColors().stream().map(c-> new ColorDto(c.getId(), c.getName())).collect(Collectors.toSet()))
            .occasions(product.getOccasions().stream().map(o -> new OccasionDto(o.getId(), o.getName())).collect(Collectors.toSet()))
            .flowerTypes(product.getFlowerTypes().stream().map(ft -> new FlowerTypeDto(ft.getId(), ft.getName())).collect(Collectors.toSet()))
            .bouquetTypeId(product.getBouquetType().getId())
            .build();
    }
}
