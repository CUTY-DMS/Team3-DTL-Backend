package com.example.dmstodo.exception;

import com.example.dmstodo.error.ErrorCode;

public class WrongPasswordException extends BusinessException{
    public WrongPasswordException(){
        super(ErrorCode.WRONG_PASSWORD);
    }
}
