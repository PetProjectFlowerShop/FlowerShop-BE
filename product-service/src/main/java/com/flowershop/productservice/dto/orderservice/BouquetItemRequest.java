package com.flowershop.productservice.dto.orderservice;

import lombok.Getter;

@Getter
public class BouquetItemRequest {
    private long productId;
    private long wrappingId;
    private int quantity;
}
