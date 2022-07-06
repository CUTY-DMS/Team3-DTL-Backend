package com.example.dmstodo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionRes {
    private int status;
    private String message;
}
