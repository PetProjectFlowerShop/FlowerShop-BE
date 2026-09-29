package com.flowershop.productservice.controller;

import com.flowershop.productservice.entity.Accessory;
import com.flowershop.productservice.service.accessory.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
