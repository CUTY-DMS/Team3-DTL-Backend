package com.example.dmstodo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ExceptionRes {
    private final int status;
    private final String message;
    private final ZonedDateTime timestamp;
}
