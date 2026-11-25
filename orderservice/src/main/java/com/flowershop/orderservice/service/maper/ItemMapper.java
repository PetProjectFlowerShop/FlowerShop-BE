package com.flowershop.orderservice.service.maper;

import com.flowershop.orderservice.dto.BouquetItemResp;
import com.flowershop.orderservice.entity.Order;
import com.flowershop.orderservice.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemMapper {
    public OrderItem mapOrderItemDtoToItem(BouquetItemResp bouquetItemResp, Order order) {
        BigDecimal unitPrice = bouquetItemResp.getOriginalBouquetPrice().add(bouquetItemResp.getWrappingPrice());
        return  OrderItem
            .builder()
            .order(order)
            .productId(bouquetItemResp.getProductId())
            .wrappingId(bouquetItemResp.getWrappingId())
            .unitPrice(unitPrice)
            .quantity(bouquetItemResp.getQuantity())
            .totalPrice(unitPrice.multiply(BigDecimal.valueOf(bouquetItemResp.getQuantity())))
            .build();
    }
}
