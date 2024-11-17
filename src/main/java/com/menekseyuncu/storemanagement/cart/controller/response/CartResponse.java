package com.menekseyuncu.storemanagement.cart.controller.response;

import com.menekseyuncu.storemanagement.cart.controller.response.CartItemResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;


@Builder
public record CartResponse(
        Long id,
        Long customerId,
        BigDecimal totalPrice,

        List<CartItemResponse> items

) {
}
