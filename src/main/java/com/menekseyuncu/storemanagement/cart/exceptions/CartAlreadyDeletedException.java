package com.menekseyuncu.storemanagement.cart.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class CartAlreadyDeletedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 9212731025302865843L;

    public CartAlreadyDeletedException() {
        super(("Cart already deleted!!"));
    }
}
