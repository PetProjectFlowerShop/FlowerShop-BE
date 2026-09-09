package com.flowershop.orderservice.service.price;

import com.flowershop.orderservice.dto.DeliveryDto;
import com.flowershop.orderservice.dto.OrderItemDto;
import java.math.BigDecimal;
import java.util.List;

public interface PricingService {
    /**
     * Calculates total price for all items in the order.
     *
     * @param items list of ordered products and their quantities
     * @return subtotal amount
     */
    BigDecimal calculateSubtotal(List<OrderItemDto> items);

    /**
     * Calculates delivery cost based on shipping type and address.
     *
     * @param delivery delivery details provided by the customer
     * @return shipping fee amount
     */
    BigDecimal calculateShippingFee(DeliveryDto delivery);
}
