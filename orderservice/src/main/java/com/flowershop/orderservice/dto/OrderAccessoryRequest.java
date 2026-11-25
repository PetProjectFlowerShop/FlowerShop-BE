package com.flowershop.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderAccessoryRequest {
    private long AccessoryId;
    private int quantity;
}
