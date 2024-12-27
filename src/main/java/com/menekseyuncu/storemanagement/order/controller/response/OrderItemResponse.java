package com.menekseyuncu.storemanagement.order.controller.response;

import java.math.BigDecimal;

public record OrderItemResponse(

        String id,
        String orderId,
        String productId,
        Long quantity,
        BigDecimal price
) {
}
