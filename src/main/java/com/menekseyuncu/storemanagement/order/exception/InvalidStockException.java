package com.menekseyuncu.storemanagement.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStockException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -6900114269331174254L;

    public InvalidStockException() {
        super("Not enough stock for product");
    }
}
