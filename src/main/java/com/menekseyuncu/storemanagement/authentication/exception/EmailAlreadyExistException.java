package com.menekseyuncu.storemanagement.authentication.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -5291581450761074771L;

    public EmailAlreadyExistException() {
        super("Email is already taken");
    }

}
