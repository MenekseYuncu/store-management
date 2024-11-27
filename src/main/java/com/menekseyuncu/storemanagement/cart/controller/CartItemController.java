package com.menekseyuncu.storemanagement.cart.controller;

import com.menekseyuncu.storemanagement.cart.controller.request.CartItemChangeQuantityRequest;
import com.menekseyuncu.storemanagement.cart.controller.request.CartItemRequest;
import com.menekseyuncu.storemanagement.cart.controller.response.CartItemResponse;
import com.menekseyuncu.storemanagement.cart.service.CartItemService;
import com.menekseyuncu.storemanagement.common.responses.BaseResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-item")
class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public BaseResponse<Void> addCartItem(
            @RequestBody @Valid final CartItemRequest cartItemRequest
    ) {
        cartItemService.addCartItem(cartItemRequest);
        return BaseResponse.SUCCESS;
    }

    @GetMapping("/{id}")
    public BaseResponse<CartItemResponse> getCartItemById(
            @PathVariable @Positive final Long id
    ) {
        CartItemResponse cartItemResponse = cartItemService.getCartItemById(id);
        return BaseResponse.successOf(cartItemResponse);
    }

    @GetMapping("/cart/{cartId}")
    public BaseResponse<List<CartItemResponse>> getCartItemsByCartId(
            @PathVariable @Positive final Long cartId
    ) {
        List<CartItemResponse> cartItems = cartItemService.getCartItemsByCartId(cartId);
        return BaseResponse.successOf(cartItems);
    }

    @PutMapping("/{id}/quantity")
    public BaseResponse<Void> updateCartItemQuantity(
            @PathVariable @Positive final Long id,
            @RequestBody @Valid final CartItemChangeQuantityRequest cartItemChangeQuantityRequest
    ) {
        cartItemService.updateCartItemQuantity(id, cartItemChangeQuantityRequest);
        return BaseResponse.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> removeCartItem(
            @PathVariable @Positive final Long id) {
        cartItemService.removeCartItem(id);
        return BaseResponse.SUCCESS;
    }
}
