package com.menekseyuncu.storemanagement.order.exception;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;

import java.io.Serial;

public class OrderItemNotFoundException extends ResourceNotFoundException {


    @Serial
    private static final long serialVersionUID = 1227429888272811310L;

    public OrderItemNotFoundException() {
        super("Order item not found!!");
    }
}
