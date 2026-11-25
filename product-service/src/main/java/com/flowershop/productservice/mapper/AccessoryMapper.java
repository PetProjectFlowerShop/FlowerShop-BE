package com.flowershop.productservice.mapper;

import com.flowershop.productservice.dto.orderservice.AccessoryItemResponse;
import com.flowershop.productservice.entity.Accessory;
import org.springframework.stereotype.Service;

@Service
public class AccessoryMapper {
    public AccessoryItemResponse mapAccessoryToAccessoryItemResp(Accessory accessory, int quantity) {
        return new AccessoryItemResponse(accessory.getId(), accessory.getPrice(), quantity);
    }
}
