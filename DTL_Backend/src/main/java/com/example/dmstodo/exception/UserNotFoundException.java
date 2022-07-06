package com.example.dmstodo.exception;


import com.example.dmstodo.error.ErrorCode;

public class UserNotFoundException extends BusinessException{

    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
