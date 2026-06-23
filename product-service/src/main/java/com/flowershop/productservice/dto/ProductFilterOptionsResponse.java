package com.flowershop.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterOptionsResponse {
    private List<FlowerTypeDto> flowerTypes;
    private List<ColorDto> colors;
    private List<BouquetTypeDto> bouquetTypes;
    private List<OccasionDto> occasions;
}
