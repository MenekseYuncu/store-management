package com.menekseyuncu.storemanagement.order.controller.response;

import com.menekseyuncu.storemanagement.cart.model.entity.CartEntity;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        CartEntity customer,
        BigDecimal totalPrice,
        List<OrderItemResponse> items
) {
}
