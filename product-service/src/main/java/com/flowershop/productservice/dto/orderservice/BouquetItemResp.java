package com.flowershop.productservice.dto.orderservice;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BouquetItemResp {
    private long productId;
    private BigDecimal productPrice;
    private int discountPercent;

    private long wrappingId;
    private BigDecimal wrappingPrice;

    private int quantity;

    public BouquetItemResp(long productId, long wrappingId, int quantity) {
        this.productId = productId;
        this.wrappingId = wrappingId;
        this.quantity = quantity;
    }

}
