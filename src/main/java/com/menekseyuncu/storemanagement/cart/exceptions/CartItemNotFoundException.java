package com.menekseyuncu.storemanagement.cart.exceptions;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;

import java.io.Serial;

public class CartItemNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = -7183708255171505689L;

    public CartItemNotFoundException() {
        super("Cart item not found !!");
    }
}