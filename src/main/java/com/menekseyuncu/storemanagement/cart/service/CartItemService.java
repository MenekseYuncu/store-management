package com.menekseyuncu.storemanagement.cart.service;

import com.menekseyuncu.storemanagement.cart.controller.request.CartItemChangeQuantityRequest;
import com.menekseyuncu.storemanagement.cart.controller.request.CartItemRequest;
import com.menekseyuncu.storemanagement.cart.controller.response.CartItemResponse;

import java.util.List;

public interface CartItemService {

    void addCartItem(CartItemRequest cartItemRequest);

    CartItemResponse getCartItemById(Long id);

    void updateCartItemQuantity(Long itemId, CartItemChangeQuantityRequest changeQuantityRequest);

    List<CartItemResponse> getCartItemsByCartId(Long cartId);

    void removeCartItem(Long id);
}
