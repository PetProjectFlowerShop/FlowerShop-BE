package com.flowershop.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderBouquetRequest {
    private long bouquetId;
    private long wrappingId;
    private int quantity;
}
