package com.example.dmstodo.exception;

public class UserAlreadyExistsExeption extends BusinessException{
    public UserAlreadyExistsExeption() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
