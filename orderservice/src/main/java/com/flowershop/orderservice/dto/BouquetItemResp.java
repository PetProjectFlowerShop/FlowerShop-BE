package com.flowershop.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BouquetItemResp {
    private long productId;
    private BigDecimal originalBouquetPrice;
    private int discountPercent;

    private long wrappingId;
    private BigDecimal wrappingPrice;

    private int quantity;
}
