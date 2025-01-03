package com.menekseyuncu.storemanagement.common.responses;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse extends BaseResponse<String> {

    public ErrorResponse(HttpStatus httpStatus, String message) {
        super(LocalDateTime.now(), httpStatus, false, message);
    }

    public static ErrorResponse failureOf(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus, message);
    }
}
