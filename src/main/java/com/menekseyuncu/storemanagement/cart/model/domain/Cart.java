package com.menekseyuncu.storemanagement.cart.model.domain;

import com.menekseyuncu.storemanagement.cart.model.entity.CartItemEntity;
import com.menekseyuncu.storemanagement.customer.model.entity.CustomerEntity;

import java.math.BigDecimal;
import java.util.List;

public record Cart(
        Long id,
        CustomerEntity customer,
        List<CartItemEntity> cartItems,
        BigDecimal totalPrice
) {
}
