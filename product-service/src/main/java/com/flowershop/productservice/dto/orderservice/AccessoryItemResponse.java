package com.flowershop.productservice.dto.orderservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccessoryItemResponse {
    private long accessoryId;
    private BigDecimal accessoryUnitPrice;
    private int quantity;
}
