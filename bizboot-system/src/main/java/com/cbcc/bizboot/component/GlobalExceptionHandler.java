package com.cbcc.bizboot.component;

import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return handleException(HttpStatus.BAD_REQUEST, new BadRequestException(message), Level.INFO);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorBody> handlerBadRequestException(BadRequestException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex, Level.INFO);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorBody> handlerServiceException(BadRequestException ex) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex, Level.ERROR);
    }

    @ResponseStatus
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorBody> handlerException(Throwable ex) {
        return handleException(ex);
    }

    private ResponseEntity<ErrorBody> handleException(Throwable ex) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex, Level.ERROR);
    }

    private ResponseEntity<ErrorBody> handleException(HttpStatus status, Throwable ex, Level logLevel) {
        printLog(ex.getMessage(), ex, logLevel);

        ErrorBody errorBody = new ErrorBody();
        errorBody.setStatus(status.value());
        errorBody.setMessage(ex.getMessage());
        errorBody.setException(ex.getClass().getSimpleName());
        errorBody.setTimestamp(LocalDateTime.now());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errorBody, headers, status);
    }

    private void printLog(String message, Throwable ex, Level logLevel) {
        switch (logLevel) {
            case ERROR:
                log.error(message, ex);
                break;
            case WARN:
                log.warn(message, ex);
                break;
            case DEBUG:
                log.debug(message, ex);
                break;
            case INFO:
                log.info(message, ex);
                break;
            case TRACE:
                log.trace(message, ex);
                break;
        }
    }
}
