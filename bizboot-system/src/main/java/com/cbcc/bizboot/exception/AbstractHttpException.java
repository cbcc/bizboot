package com.cbcc.bizboot.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public abstract class AbstractHttpException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1713129594004951820L;

    private HttpStatus httpStatus;

    public AbstractHttpException(String message) {
        super(message);
    }

    public AbstractHttpException(String message, Exception ex) {
        super(message, ex);
    }

    protected void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
