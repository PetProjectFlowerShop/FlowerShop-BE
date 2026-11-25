package com.flowershop.productservice.dto.orderservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessoryItemRequest {
    private long accessoryId;
    private int quantity;

}
