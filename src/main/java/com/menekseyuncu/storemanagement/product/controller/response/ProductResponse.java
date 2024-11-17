package com.menekseyuncu.storemanagement.product.controller.response;

import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record ProductResponse(
        String name,
        BigDecimal price,
        Long stock
) {
}