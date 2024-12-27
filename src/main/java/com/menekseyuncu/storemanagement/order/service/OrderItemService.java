package com.menekseyuncu.storemanagement.order.service;

import com.menekseyuncu.storemanagement.order.controller.request.OrderItemRequest;
import com.menekseyuncu.storemanagement.order.controller.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getAllOrderItems();

    List<OrderItemResponse> getOrderItemsByOrder(Long orderId);

    OrderItemResponse getOrderItemById(Long id);

    void createOrderItem(OrderItemRequest orderItemRequest);

    void deleteOrderItem(Long id);
}