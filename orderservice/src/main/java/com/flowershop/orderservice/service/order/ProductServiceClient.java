package com.flowershop.orderservice.service.order;

import com.flowershop.orderservice.dto.AccessoryItemResp;
import com.flowershop.orderservice.dto.OrderAccessoryRequest;
import com.flowershop.orderservice.dto.OrderBouquetRequest;
import com.flowershop.orderservice.dto.BouquetItemResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(
    name = "product-service",           // Логическое имя клиента
    url = "${product.service.url}"      // Берёт значение из нашего application.yml
)
public interface ProductServiceClient {

    // Путь и метод должны строго совпадать с контроллером в Product Service!
    @PostMapping("/flowers/get-product-price")
    List<BouquetItemResp> checkAndGetProductPrices(@RequestBody List<OrderBouquetRequest> request);

    @PostMapping("/flowers/accessory/get-price")
    List<AccessoryItemResp> checkAndGetAccessoryPrices(@RequestBody List<OrderAccessoryRequest> request);
}
