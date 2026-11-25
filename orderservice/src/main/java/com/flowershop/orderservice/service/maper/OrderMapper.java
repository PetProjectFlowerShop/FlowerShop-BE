package com.flowershop.orderservice.service.maper;

import com.flowershop.orderservice.dto.CreateOrderRequest;
import com.flowershop.orderservice.entity.Order;
import com.flowershop.orderservice.entity.OrderStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderMapper {
    public Order mapCreateRequestToOrder (CreateOrderRequest request,
                                          BigDecimal subtotalAmount, BigDecimal discountAmount, BigDecimal shippingAmount,
                                          BigDecimal totalAmount) {
        return Order.builder()
            .status(OrderStatus.PENDING)
            .customerName(request.getCustomer().getName())
            .customerEmail(request.getCustomer().getEmail())
            .customerPhone(request.getCustomer().getPhone())
            .dontCall(request.getCustomer().isDontCall())
            .isAnotherRecipient(request.getRecipient().isAnotherRecipient())
            .recipientName(request.getRecipient().getName())
            .recipientPhone(request.getRecipient().getPhone())
            .hasGreetingCard(request.getGreetingCard().isEnabled())
            .greetingCardText(request.getGreetingCard().getText())
            .promoCode(request.getPromoCode())
            .subtotalAmount(subtotalAmount)
            .discountAmount(discountAmount)
            .shippingAmount(shippingAmount)
            .totalAmount(totalAmount)
            .paymentMethod(request.getPaymentMethod())
            .createdAt(LocalDateTime.now())
            .build();
    }
}
