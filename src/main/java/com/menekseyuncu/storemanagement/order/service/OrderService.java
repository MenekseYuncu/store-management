package com.menekseyuncu.storemanagement.order.service;

import com.menekseyuncu.storemanagement.order.controller.request.OrderRequest;
import com.menekseyuncu.storemanagement.order.controller.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getOrdersByCustomerId(Long customerId);


    void createOrder(OrderRequest orderRequest);

    void placeOrder(Long id);

    void deleteOrder(Long id);
}