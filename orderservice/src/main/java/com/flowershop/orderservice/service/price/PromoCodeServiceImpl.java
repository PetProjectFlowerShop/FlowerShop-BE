package com.flowershop.orderservice.service.price;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    //floria10
    public BigDecimal calculateDiscount(String promoCode, BigDecimal subtotal) {
        return promoCode != null && !promoCode.isEmpty()
            ? subtotal.multiply(BigDecimal.valueOf(0.1))
            : BigDecimal.ZERO;
    }
}
