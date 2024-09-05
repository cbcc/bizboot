package com.cbcc.bizboot.component;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorBody {

    private int status;

    private String message;

    private String exception;

    private LocalDateTime timestamp;
}
