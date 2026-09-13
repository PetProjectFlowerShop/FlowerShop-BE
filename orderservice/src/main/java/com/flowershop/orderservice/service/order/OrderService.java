package com.flowershop.orderservice.service.order;

import com.flowershop.orderservice.dto.CreateOrderRequest;
import com.flowershop.orderservice.dto.OrderResponseDto;

/**
 * Main service for orchestrating the order creation process.
 */
public interface OrderService {

    /**
     * Validates order request, calculates totals, saves order, and prepares payment.
     *
     * @param request customer order payload
     * @return created order response details
     */
    OrderResponseDto createOrder(CreateOrderRequest request);
}
