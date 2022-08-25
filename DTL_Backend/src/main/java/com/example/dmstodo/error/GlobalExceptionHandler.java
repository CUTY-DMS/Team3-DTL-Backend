package com.example.dmstodo.error;

import com.example.dmstodo.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionRes> handleException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ExceptionRes(errorCode.getStatus(), errorCode.getMessage(), ZonedDateTime.now()), HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionRes> handleValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ExceptionRes(400, e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), ZonedDateTime.now()), HttpStatus.valueOf(400));
    }
}
