package com.flowershop.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String name;
    private String email;
    private String phone;
    private boolean dontCall;
}
