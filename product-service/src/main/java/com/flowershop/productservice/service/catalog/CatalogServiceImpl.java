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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatalogServiceImpl implements CatalogService {
    private final ProductRepository productRepository;
    private final FilterMapper filterMapper;

    @Override
    public Page<ProductFilterResponse> getSearchedProducts(ProductFilterRequest request) {
        Pageable pageable = buildPage(request);
        Specification<Product> spec = ProductSpecificationBuilder.build(request);
        Page<Product> page = productRepository.findAll(spec, pageable);
        List<Long> ids = page.getContent().stream()
            .map(Product::getId)
            .toList();
        productRepository.findAllByIds(ids);
        return page.map(filterMapper::mapProductToResponse);
    }

    private Pageable buildPage(ProductFilterRequest filter) {
        int page = filter.getPage() != null ? filter.getPage() : 0;
        int size = filter.getSize() != null ? filter.getSize() : 10;
        Sort sort = ProductSorter.buildSort(filter);
        return PageRequest.of(page, size, sort);
    }

}
