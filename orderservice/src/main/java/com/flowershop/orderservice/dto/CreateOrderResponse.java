package com.flowershop.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CreateOrderResponse {
    private long orderNumber;
    BigDecimal subTotalOriginalAmount;
    BigDecimal discountAmount;
    BigDecimal shippingAmount;
    BigDecimal totalAmount;

    private CustomerDto customer;
    private RecipientDto recipient;
    private DeliveryDto delivery;

    private List<OrderBouquetResponse> bouquets;
    private List<OrderAccessoryResponse> accessories;
}
