package com.menekseyuncu.storemanagement.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerAlreadyDeletedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2574626829050249269L;

    public CustomerAlreadyDeletedException() {
        super(("Customer already deleted!!"));
    }
}
