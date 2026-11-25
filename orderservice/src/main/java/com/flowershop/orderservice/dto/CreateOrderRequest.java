package com.flowershop.orderservice.dto;

import com.flowershop.orderservice.entity.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    @Valid
    @NotNull(message = "Customer details are required")
    private CustomerDto customer;

    @Valid
    @NotNull(message = "Recipient details are required")
    private RecipientDto recipient;

    @Valid
    @NotNull(message = "Greeting card details are required")
    private GreetingCardDto greetingCard;


    @Valid
    @NotNull(message = "Delivery details are required")
    private DeliveryDto delivery;

    private String promoCode;

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderBouquetRequest> items;

    private List<OrderAccessoryRequest> accessories;

    private PaymentMethod paymentMethod;
}
