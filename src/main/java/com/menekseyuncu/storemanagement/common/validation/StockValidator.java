package com.menekseyuncu.storemanagement.common.validation;

import com.menekseyuncu.storemanagement.order.exception.InvalidStockException;
import com.menekseyuncu.storemanagement.order.model.entity.OrderEntity;
import com.menekseyuncu.storemanagement.order.model.entity.OrderItemEntity;
import com.menekseyuncu.storemanagement.product.exceptions.ProductNotFoundException;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import com.menekseyuncu.storemanagement.product.repository.ProductRepository;
import lombok.experimental.UtilityClass;


@UtilityClass
public class StockValidator {

    private static ProductRepository productRepository;

    /**
     * Validates stock for the given order.
     *
     * @param order the order to validate
     */
    public static void validate(OrderEntity order) {
        if (order == null || order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            return;
        }

        for (OrderItemEntity item : order.getOrderItems()) {
            Long productId = item.getProduct().getId();
            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(ProductNotFoundException::new);

            if (product.getStock() < item.getQuantity()) {
                throw new InvalidStockException();
            }
        }
    }
}
