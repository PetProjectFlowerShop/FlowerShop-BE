package com.flowershop.productservice.controller;

import com.flowershop.productservice.dto.orderservice.AccessoryItemRequest;
import com.flowershop.productservice.dto.orderservice.AccessoryItemResponse;
import com.flowershop.productservice.entity.Accessory;
import com.flowershop.productservice.service.accessory.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flowers/accessories")
public class AccessoryController {
    private final AccessoryService accessoryService;

    @GetMapping
    public List<Accessory> getAllAccessories() {
        return accessoryService.getAllAccessories();
    }

    @PostMapping("/get-price")
    public List<AccessoryItemResponse> getAccessoriesPrices(@RequestBody List<AccessoryItemRequest> request) {
        return accessoryService.getAccessoriesPrices(request);
    }
}
