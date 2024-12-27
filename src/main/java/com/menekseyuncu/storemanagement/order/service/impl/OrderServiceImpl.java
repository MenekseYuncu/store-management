package com.menekseyuncu.storemanagement.order.service.impl;

import com.menekseyuncu.storemanagement.common.validation.StockValidator;
import com.menekseyuncu.storemanagement.order.controller.request.OrderRequest;
import com.menekseyuncu.storemanagement.order.controller.response.OrderResponse;
import com.menekseyuncu.storemanagement.order.exception.OrderNotFoundException;
import com.menekseyuncu.storemanagement.order.model.domain.Order;
import com.menekseyuncu.storemanagement.order.model.entity.OrderEntity;
import com.menekseyuncu.storemanagement.order.model.entity.OrderItemEntity;
import com.menekseyuncu.storemanagement.order.model.mapper.OrderEntityToOrderMapper;
import com.menekseyuncu.storemanagement.order.model.mapper.OrderRequestToOrderEntityMapper;
import com.menekseyuncu.storemanagement.order.model.mapper.OrderToOrderResponseMapper;
import com.menekseyuncu.storemanagement.order.repository.OrderRepository;
import com.menekseyuncu.storemanagement.order.service.OrderService;
import com.menekseyuncu.storemanagement.product.exceptions.ProductNotFoundException;
import com.menekseyuncu.storemanagement.product.model.entity.ProductEntity;
import com.menekseyuncu.storemanagement.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final OrderRequestToOrderEntityMapper orderRequestToOrderEntityMapper = OrderRequestToOrderEntityMapper.INSTANCE;
    private static final OrderEntityToOrderMapper entityToDomainMapper = OrderEntityToOrderMapper.INSTANCE;
    private static final OrderToOrderResponseMapper orderToResponseMapper = OrderToOrderResponseMapper.INSTANCE;

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Override
    public OrderResponse getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(OrderNotFoundException::new);

        Order order = entityToDomainMapper.map(orderEntity);

        return orderToResponseMapper.map(order);
    }

    @Override
    public List<OrderResponse> getOrdersByCustomerId(Long customerId) {
        List<OrderEntity> orderEntities = orderRepository.findByCustomerIdAndDeletedAtIsNull(customerId);

        return orderEntities.stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    private OrderResponse mapToOrderResponse(OrderEntity orderEntity) {
        Order order = entityToDomainMapper.map(orderEntity);
        return orderToResponseMapper.map(order);
    }


    @Override
    public void createOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = orderRequestToOrderEntityMapper.map(orderRequest);
        orderEntity.setTotalPrice(BigDecimal.ZERO);
        orderRepository.save(orderEntity);
    }

    @Override
    @Transactional
    public void placeOrder(Long id) {
        OrderEntity order = orderRepository.findById(id).
                orElseThrow(OrderNotFoundException::new);

        validateStock(order);
        order.setTotalPrice(calculateTotalPrice(order));

        orderRepository.save(order);

    }

    private void validateStock(OrderEntity order) {
        for (OrderItemEntity item : order.getOrderItems()) {
            ProductEntity product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(ProductNotFoundException::new);

            StockValidator.validate(order);

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            item.setUnitPrice(product.getPrice());
            item.setOrder(order);
        }
    }

    private BigDecimal calculateTotalPrice(OrderEntity order) {
        return order.getOrderItems().stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void deleteOrder(Long id) {
        OrderEntity order = orderRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(OrderNotFoundException::new);

        if (order.isDeleted()) {
            throw new OrderNotFoundException();
        }

        order.softDelete();
        orderRepository.save(order);
    }
}
