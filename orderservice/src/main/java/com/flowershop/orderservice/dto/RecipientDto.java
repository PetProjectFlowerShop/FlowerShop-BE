package com.flowershop.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientDto {
    private boolean isAnotherRecipient;
    private String name;
    private String phone;
}
