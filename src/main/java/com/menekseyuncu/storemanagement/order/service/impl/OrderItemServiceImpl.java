package com.menekseyuncu.storemanagement.order.service.impl;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;
import com.menekseyuncu.storemanagement.order.controller.request.OrderItemRequest;
import com.menekseyuncu.storemanagement.order.controller.response.OrderItemResponse;
import com.menekseyuncu.storemanagement.order.exception.InvalidOrderItemException;
import com.menekseyuncu.storemanagement.order.exception.OrderItemNotFoundException;
import com.menekseyuncu.storemanagement.order.exception.OrderNotFoundException;
import com.menekseyuncu.storemanagement.order.model.entity.OrderEntity;
import com.menekseyuncu.storemanagement.order.model.entity.OrderItemEntity;
import com.menekseyuncu.storemanagement.order.model.mapper.OrderItemEntityToResponseMapper;
import com.menekseyuncu.storemanagement.order.repository.OrderItemRepository;
import com.menekseyuncu.storemanagement.order.repository.OrderRepository;
import com.menekseyuncu.storemanagement.order.service.OrderItemService;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import com.menekseyuncu.storemanagement.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemRepository.findAllByDeletedAtIsNull().stream()
                .map(OrderItemEntityToResponseMapper.INSTANCE::map)
                .toList();
    }

    @Override
    public List<OrderItemResponse> getOrderItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderIdAndDeletedAtIsNull(orderId).stream()
                .map(OrderItemEntityToResponseMapper.INSTANCE::map)
                .toList();    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        return orderItemRepository.findByIdAndDeletedAtIsNull(id)
                .map(OrderItemEntityToResponseMapper.INSTANCE::map)
                .orElseThrow(OrderItemNotFoundException::new);
    }

    @Override
    @Transactional
    public void createOrderItem(OrderItemRequest orderItemRequest) {
        ProductEntity product = validateProductAndStock(orderItemRequest);

        OrderEntity order = orderRepository.findByIdAndDeletedAtIsNull(orderItemRequest.orderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderItemRequest.orderId()));

        Optional<OrderItemEntity> existingOrderItemOpt = orderItemRepository
                .findByOrderIdAndProductIdAndDeletedAtIsNull(order.getId(), product.getId());

        if (existingOrderItemOpt.isPresent()) {
            OrderItemEntity existingOrderItem = existingOrderItemOpt.get();
            existingOrderItem.setQuantity(existingOrderItem.getQuantity() + orderItemRequest.quantity());
            existingOrderItem.setUnitPrice(existingOrderItem.getUnitPrice().add(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.quantity()))));
            orderItemRepository.save(existingOrderItem);
            return;
        }
        OrderItemEntity orderItem = buildOrderItem(orderItemRequest, product, order);
        orderItemRepository.save(orderItem);


        updateProductStock(product, orderItemRequest.quantity());
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItemEntity orderItem = orderItemRepository.findByOrderIdAndDeletedAtIsNull(id)
                .orElseThrow(OrderItemNotFoundException::new);

        if (orderItem.isDeleted()) {
            throw new OrderNotFoundException();
        }

        orderItem.softDelete();
        orderItemRepository.save(orderItem);
    }


    private ProductEntity validateProductAndStock(OrderItemRequest orderItemRequest) {
        ProductEntity product = productRepository.findById(orderItemRequest.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderItemRequest.productId()));

        if (product.getStock() < orderItemRequest.quantity()) {
            throw new InvalidOrderItemException();
        }
        return product;
    }

    private OrderItemEntity buildOrderItem(OrderItemRequest orderItemRequest, ProductEntity product, OrderEntity order) {

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.quantity()));

        return OrderItemEntity.builder()
                .order(order)
                .product(product)
                .quantity(orderItemRequest.quantity())
                .unitPrice(totalPrice)
                .build();
    }

    private void updateProductStock(ProductEntity product, Long quantity) {
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }
}
