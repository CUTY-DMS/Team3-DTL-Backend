package com.example.dmstodo.exception;

import com.example.dmstodo.error.ErrorCode;

public class UserAlreadyExistsExeption extends BusinessException {
    public UserAlreadyExistsExeption() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
