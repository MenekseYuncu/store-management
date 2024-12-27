package com.menekseyuncu.storemanagement.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderItemException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -6198127979955212788L;

    public InvalidOrderItemException() {
        super("Not enough stock for product");
    }

}

