package com.menekseyuncu.storemanagement.order.controller;

import com.menekseyuncu.storemanagement.common.responses.BaseResponse;
import com.menekseyuncu.storemanagement.order.controller.request.OrderItemRequest;
import com.menekseyuncu.storemanagement.order.controller.response.OrderItemResponse;
import com.menekseyuncu.storemanagement.order.service.OrderItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public BaseResponse<OrderItemResponse> getOrderItemById(
            @PathVariable @Positive final Long id
    ) {
        OrderItemResponse orderItemResponse = orderItemService.getOrderItemById(id);
        return BaseResponse.successOf(orderItemResponse);
    }

    @GetMapping("/items")
    public BaseResponse<List<OrderItemResponse>> getAllOrderItems() {
        List<OrderItemResponse> orderItems = orderItemService.getAllOrderItems();
        return BaseResponse.successOf(orderItems);
    }

    @GetMapping("/{orderId}")
    public BaseResponse<List<OrderItemResponse>> getOrderItemsByOrder(
            @PathVariable @Positive Long orderId
    ) {
        List<OrderItemResponse> orderItems = orderItemService.getOrderItemsByOrder(orderId);
        return BaseResponse.successOf(orderItems);
    }

    @PostMapping
    public BaseResponse<Void> createOrderItem(
            @RequestBody @Valid OrderItemRequest orderItemRequest
    ) {
        orderItemService.createOrderItem(orderItemRequest);
        return BaseResponse.SUCCESS;
    }

    @DeleteMapping("{id}")
    public BaseResponse<Void> deleteOrderItem(
            @PathVariable @Positive final Long id
    ) {
        orderItemService.deleteOrderItem(id);
        return BaseResponse.SUCCESS;
    }

}
