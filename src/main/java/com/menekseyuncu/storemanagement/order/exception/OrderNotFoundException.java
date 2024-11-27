package com.menekseyuncu.storemanagement.order.exception;

import com.menekseyuncu.storemanagement.common.exceptions.ResourceNotFoundException;

import java.io.Serial;

public class OrderNotFoundException extends ResourceNotFoundException {


    @Serial
    private static final long serialVersionUID = -5645674944802736545L;

    public OrderNotFoundException() {
        super("Order not found!!");
    }
}
