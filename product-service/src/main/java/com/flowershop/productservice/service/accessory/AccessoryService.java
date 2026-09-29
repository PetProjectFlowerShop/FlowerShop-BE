package com.flowershop.productservice.service.accessory;

import com.flowershop.productservice.entity.Accessory;
import com.flowershop.productservice.repository.AccessoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;

    @Cacheable(value = "getAllAccessories")
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }
}
