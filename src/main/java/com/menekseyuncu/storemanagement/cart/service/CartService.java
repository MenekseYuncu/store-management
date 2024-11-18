package com.menekseyuncu.storemanagement.cart.service;

import com.menekseyuncu.storemanagement.cart.controller.request.CartCreateRequest;
import com.menekseyuncu.storemanagement.cart.model.domain.Cart;

public interface CartService {

    void createCart(CartCreateRequest cartRequest);

    Cart getCartByCustomerId(Long customerId);

    void emptyCart(Long id);

    void deleteCart(Long id);
}
