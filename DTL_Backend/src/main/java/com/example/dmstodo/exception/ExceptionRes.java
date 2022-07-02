package com.example.dmstodo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionRes {
    private int status;
    private String message;
}
