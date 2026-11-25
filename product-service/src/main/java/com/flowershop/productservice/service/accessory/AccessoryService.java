package com.flowershop.productservice.service.accessory;

import com.flowershop.productservice.dto.orderservice.AccessoryItemRequest;
import com.flowershop.productservice.dto.orderservice.AccessoryItemResponse;
import com.flowershop.productservice.entity.Accessory;
import com.flowershop.productservice.exceptions.NotFoundException;
import com.flowershop.productservice.mapper.AccessoryMapper;
import com.flowershop.productservice.repository.AccessoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final AccessoryMapper mapper;

    @Cacheable(value = "getAllAccessories")
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    public Accessory getAccessoryById (long id) {
        return accessoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Accessory id : " + id + " not found"));
    }

    public List<AccessoryItemResponse> getAccessoriesPrices(List<AccessoryItemRequest> request) {
        List<AccessoryItemResponse> response = new ArrayList<>();
        for (AccessoryItemRequest item : request) {
            Accessory accessory = getAccessoryById(item.getAccessoryId());
            AccessoryItemResponse accessoryItemResponse = mapper.mapAccessoryToAccessoryItemResp(accessory, item.getQuantity());
            response.add(accessoryItemResponse);

        }
        return response;
    }
}
