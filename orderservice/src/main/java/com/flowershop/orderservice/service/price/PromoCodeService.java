package com.flowershop.orderservice.service.price;

import java.math.BigDecimal;

public interface PromoCodeService {

    /**
     * Calculates the discount for the given promo code.
     *
     * @param promoCode promo code provided by the customer
     * @param subtotal order subtotal
     * @return discount amount
     */
    BigDecimal calculateDiscount(String promoCode, BigDecimal subtotal);
}
