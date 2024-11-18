package com.menekseyuncu.storemanagement.cart.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemChangeQuantityRequest(
        @NotNull
        @Positive
        Long quantity
) {
}
