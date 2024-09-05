package com.cbcc.bizboot.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractHttpException {

    public BadRequestException(String message) {
        super(message);
        setHttpStatus(HttpStatus.BAD_REQUEST);
    }
}
