package com.menekseyuncu.storemanagement.cart.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CartItemRequest(
        @NotNull
        Long cartId,

        @NotNull
        Long productId,

        @NotNull
        @Positive
        Long quantity,

        @NotNull
        @Positive
        BigDecimal price
) {
}
