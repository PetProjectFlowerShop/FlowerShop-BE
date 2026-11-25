package com.flowershop.orderservice.service.order;

import com.flowershop.orderservice.dto.*;
import com.flowershop.orderservice.entity.*;
import com.flowershop.orderservice.repository.DeliveryRepository;
import com.flowershop.orderservice.repository.OrderItemRepository;
import com.flowershop.orderservice.repository.OrderRepository;
import com.flowershop.orderservice.service.maper.DeliveryMapper;
import com.flowershop.orderservice.service.maper.ItemMapper;
import com.flowershop.orderservice.service.maper.OrderMapper;
import com.flowershop.orderservice.service.price.PricingService;
import com.flowershop.orderservice.service.price.PromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PricingService pricingService;
    private final PromoCodeService promoCodeService;
    private final ProductServiceClient productServiceClient;
    private final OrderMapper orderMapper;
    private final DeliveryMapper deliveryMapper;
    private final ItemMapper itemMapper;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        CreateOrderResponse dto = new CreateOrderResponse();
        List<BouquetItemResp> bouquetItemResps = getProductsInfo(request.getItems());
        BigDecimal subtotalAmount = pricingService.calculateSubtotal(bouquetItemResps);
        BigDecimal shippingAmount = pricingService.calculateShippingFee(request.getDelivery());
        BigDecimal discount = promoCodeService.calculateDiscount(request.getPromoCode(), subtotalAmount);
        BigDecimal totalAmount = pricingService.calculateTotalAmount(subtotalAmount, shippingAmount, discount);
        Order order = orderMapper.mapCreateRequestToOrder(request, subtotalAmount, discount, shippingAmount, totalAmount);
        orderRepository.save(order);

        Delivery delivery = deliveryMapper.mapDeliveryDtotoDelivery(request.getDelivery(), order);
        deliveryRepository.save(delivery);

        for (BouquetItemResp item : bouquetItemResps) {
            orderItemRepository.save(itemMapper.mapOrderItemDtoToItem(item, order));
        }
        dto.setTotalAmount(order.getTotalAmount());
        dto.setDelivery(request.getDelivery());
//        dto.setBouquets(request.getItems());
        return dto;
    }

    List<BouquetItemResp> getProductsInfo(List<OrderBouquetRequest> items) {
        return productServiceClient.checkAndGetProductPrices(items);
    }
}
