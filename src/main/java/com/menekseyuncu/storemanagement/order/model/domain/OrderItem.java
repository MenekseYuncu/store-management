package com.menekseyuncu.storemanagement.order.model.domain;

import com.menekseyuncu.storemanagement.order.model.entity.OrderEntity;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Long id;
    private OrderEntity order;
    private ProductEntity product;
    private BigDecimal unitPrice;
    private Long quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}