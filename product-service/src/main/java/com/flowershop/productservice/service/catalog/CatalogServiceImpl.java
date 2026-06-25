package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterRequest;
import com.flowershop.productservice.dto.ProductFilterResponse;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.mapper.FilterMapper;
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

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final ProductRepository productRepository;
    private final FilterMapper filterMapper;

    @Override
    public Page<ProductFilterResponse> getSearchedProducts(ProductFilterRequest request) {
        Pageable pageable = buildPage(request);
        Specification<Product> spec = ProductSpecificationBuilder.build(request);
        return productRepository.findAll(spec, pageable)
            .map(filterMapper::mapProductToResponse);
    }

    private Pageable buildPage(ProductFilterRequest filter) {
        int page = filter.getPage() != null ? filter.getPage() : 0;
        int size = filter.getSize() != null ? filter.getSize() : 10;
        Sort sort = ProductSorter.buildSort(filter);
        return PageRequest.of(page, size, sort);
    }

}
