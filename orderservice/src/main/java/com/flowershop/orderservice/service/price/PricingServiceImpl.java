package com.flowershop.orderservice.service.price;

import com.flowershop.orderservice.dto.AccessoryItemResp;
import com.flowershop.orderservice.dto.DeliveryDto;
import com.flowershop.orderservice.dto.BouquetItemResp;
import com.flowershop.orderservice.entity.DeliveryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {
    private final PromoCodeService promoCodeService;

    @Override
    public BigDecimal calculateSubtotal(List<BouquetItemResp> bouquets) {
        BigDecimal res = BigDecimal.ZERO;
        for (BouquetItemResp bouquet : bouquets) {
            long prodQuantity = bouquet.getQuantity();
            res = res.add(bouquet.getOriginalBouquetPrice().add(bouquet.getWrappingPrice())
                .multiply(BigDecimal.valueOf(prodQuantity)));
        }
        return res;
    }

    @Override
    public BigDecimal calculateShippingFee(DeliveryDto delivery) {
        return delivery.getDeliveryType() == DeliveryType.COURIER ? BigDecimal.valueOf(5) : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateTotalAmount (BigDecimal subtotal, BigDecimal shipping, BigDecimal discount) {
        return subtotal.subtract(discount).add(shipping);
    }

    public BigDecimal calculateDiscountedBouquetPrice(BouquetItemResp bouquet) {
        return bouquet.getOriginalBouquetPrice()
            .multiply(BigDecimal.valueOf(bouquet.getDiscountPercent()/100D));
    }

    public static BigDecimal calculateTotalDiscountWithoutPromo(List<BouquetItemResp> bouquets) {
        BigDecimal discount = BigDecimal.ZERO;
        for (BouquetItemResp bouquet : bouquets) {
            discount = discount
                .add(bouquet.getOriginalBouquetPrice()
                .multiply(BigDecimal.valueOf((double)bouquet.getDiscountPercent()/100D))
                .multiply(BigDecimal.valueOf(bouquet.getQuantity())));
        }
        return discount;
    }

//    public static void main(String[] args) {
//        BouquetItemResp bouquet1 = new BouquetItemResp();
//        bouquet1.setQuantity(2);
//        bouquet1.setDiscountPercent(23);
//        bouquet1.setOriginalBouquetPrice(BigDecimal.valueOf(100));
//
//        BouquetItemResp bouquet2 = new BouquetItemResp();
//        bouquet2.setQuantity(2);
//        bouquet2.setDiscountPercent(20);
//        bouquet2.setOriginalBouquetPrice(BigDecimal.valueOf(200));
//
//        List<BouquetItemResp> bouquets = List.of(bouquet2, bouquet1);
//
//        System.out.println(calculateTotalDiscountWithoutPromo(bouquets));
//    }

}
