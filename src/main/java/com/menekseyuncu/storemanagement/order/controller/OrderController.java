package com.menekseyuncu.storemanagement.order.controller;


import com.menekseyuncu.storemanagement.common.responses.BaseResponse;
import com.menekseyuncu.storemanagement.order.controller.request.OrderRequest;
import com.menekseyuncu.storemanagement.order.controller.response.OrderResponse;
import com.menekseyuncu.storemanagement.order.service.OrderService;
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
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public BaseResponse<OrderResponse> getOrderById(
            @PathVariable @Positive final Long id
    ) {
        OrderResponse orderResponse = orderService.getOrderById(id);
        return BaseResponse.successOf(orderResponse);
    }

    @GetMapping("/customer/{customerId}")
    public BaseResponse<List<OrderResponse>> getOrdersByCustomerId(
            @PathVariable @Positive final Long customerId
    ) {
        List<OrderResponse> orders = orderService.getOrdersByCustomerId(customerId);
        return BaseResponse.successOf(orders);
    }

    @PostMapping
    public BaseResponse<Void> createOrder(
            @RequestBody @Valid OrderRequest orderRequest
    ) {
        orderService.createOrder(orderRequest);
        return BaseResponse.SUCCESS;
    }


    @PostMapping("/place/{id}")
    public BaseResponse<Void> placeOrder(
            @PathVariable @Valid @Positive final Long id) {
        orderService.placeOrder(id);
        return BaseResponse.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteOrder(
            @PathVariable @Positive final Long id
    ) {
        orderService.deleteOrder(id);
        return BaseResponse.SUCCESS;
    }

}
