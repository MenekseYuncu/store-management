package com.menekseyuncu.storemanagement.cart.controller.response;

import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record CartItemResponse(
        Long id,
        Long cartId,
        Long productId,
        Long quantity,
        BigDecimal price
) {
}
