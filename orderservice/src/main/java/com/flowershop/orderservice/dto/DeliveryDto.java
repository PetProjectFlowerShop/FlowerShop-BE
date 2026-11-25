package com.flowershop.orderservice.dto;

import com.flowershop.orderservice.entity.DeliveryType;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class DeliveryDto {
    private DeliveryType deliveryType;
    private String city;
    private String street;
    private String buildingNumber;
    private String entranceNumber;
    private String apartmentNumber;
    private String pickupStoreAddress;
    private String deliveryDate;
    private String deliveryTimeSlot;
    private String deliveryComment;
}
