package com.menekseyuncu.storemanagement.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2519267781117976117L;

    public ResourceNotFoundException(String s) {
        super("Resource not found!!");
    }
}