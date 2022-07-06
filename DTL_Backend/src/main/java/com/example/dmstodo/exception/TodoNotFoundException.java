package com.example.dmstodo.exception;

import com.example.dmstodo.error.ErrorCode;

public class TodoNotFoundException extends BusinessException{
    public TodoNotFoundException() {
        super(ErrorCode.TODO_NOT_FOUND);
    }
}
