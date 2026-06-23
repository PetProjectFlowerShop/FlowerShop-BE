package com.flowershop.productservice.service.catalog;

import com.flowershop.productservice.dto.ProductFilterOptionsResponse;
import com.flowershop.productservice.mapper.FilterMapper;
import com.flowershop.productservice.repository.BouquetTypeRepository;
import com.flowershop.productservice.repository.ColorRepository;
import com.flowershop.productservice.repository.FlowerTypeRepository;
import com.flowershop.productservice.repository.OccasionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterOptionsServiceImpl implements FilterOptionsService {
    private final ColorRepository colorRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final BouquetTypeRepository bouquetTypeRepository;
    private final OccasionRepository occasionRepository;
    private final FilterMapper filterMapper;

    @Override
    @Cacheable(value = "filterOptions")
    @Transactional(readOnly = true)
    public ProductFilterOptionsResponse getFilterOptions() {
        return ProductFilterOptionsResponse.builder()
            .flowerTypes(flowerTypeRepository.findAll().stream()
                .map(filterMapper::mapToFlowerTypeDto).collect(Collectors.toList()))
            .colors(colorRepository.findAll().stream()
                .map(filterMapper::mapToColorDto).collect(Collectors.toList()))
            .bouquetTypes(bouquetTypeRepository.findAll().stream()
                .map(b -> filterMapper.mapToBouquetTypeDto(b, b.getPackagingTypes())).collect(Collectors.toList()))
            .occasions(occasionRepository.findAll().stream()
                .map(filterMapper::mapToOccasionDto).collect(Collectors.toList()))
            .build();
    }
}

