package com.menekseyuncu.storemanagement.cart.exceptions;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;

import java.io.Serial;

public class CartNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = -7207935580995457490L;

    public CartNotFoundException() {
        super("Cart not found !!");
    }
}
