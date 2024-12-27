package com.menekseyuncu.storemanagement.order.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderItemRequest(

        @NotNull
        Long productId,

        @NotNull
        Long orderId,

        @NotNull
        @Positive
        Long quantity,

        @NotNull
        @Positive
        BigDecimal price
) {
}
