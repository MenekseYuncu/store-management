package com.menekseyuncu.storemanagement.authentication.exception;

import java.io.Serial;

public class AuthenticationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2160838368602969453L;


    private static final String DEFAULT_MESSAGE = """
            Authentication failed!
            """;

    public AuthenticationException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
