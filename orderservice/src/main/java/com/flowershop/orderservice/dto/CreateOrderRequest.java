package com.flowershop.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateOrderRequest {
    @Valid
    @NotNull(message = "Delivery details are required")
    private DeliveryDto delivery;

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemDto> items;
}
