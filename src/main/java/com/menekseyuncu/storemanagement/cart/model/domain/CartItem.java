package com.menekseyuncu.storemanagement.cart.model.domain;

import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;

import java.math.BigDecimal;

public record CartItem(
        Long id,
        CartEntity cart,
        ProductEntity product,
        Long quantity,
        BigDecimal price
) {
}
