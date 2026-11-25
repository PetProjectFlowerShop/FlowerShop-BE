package com.flowershop.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class AccessoryItemResp {
    private long accessoryId;
    private BigDecimal accessoryUnitPrice;
    private int quantity;
}
