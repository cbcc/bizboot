package com.cbcc.bizboot.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends AbstractHttpException {

    public ServiceException(String message) {
        super(message);
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ServiceException(String message, Exception ex) {
        super(message, ex);
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
