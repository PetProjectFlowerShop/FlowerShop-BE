package com.flowershop.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BouquetTypeDto {
    private Long id;
    private String name;
    private Set<PackagingTypeDto> packagingTypes;
}
