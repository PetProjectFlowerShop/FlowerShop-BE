package com.flowershop.orderservice.service.maper;

import com.flowershop.orderservice.dto.DeliveryDto;
import com.flowershop.orderservice.entity.Delivery;
import com.flowershop.orderservice.entity.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeliveryMapper {
    public Delivery mapDeliveryDtotoDelivery(DeliveryDto deliveryDto, Order order) {
        return Delivery.builder()
            .order(order)
            .type(deliveryDto.getDeliveryType())
            .city(deliveryDto.getCity())
            .street(deliveryDto.getStreet())
            .buildingNumber(deliveryDto.getBuildingNumber())
            .entranceNumber(deliveryDto.getEntranceNumber())
            .apartmentNumber(deliveryDto.getApartmentNumber())
            .pickupStoreAddress(deliveryDto.getPickupStoreAddress())
            .deliveryDate(LocalDate.parse(deliveryDto.getDeliveryDate()))
            .deliveryTimeSlot(deliveryDto.getDeliveryTimeSlot())
            .deliveryComment(deliveryDto.getDeliveryComment())
            .build();
    }
}
