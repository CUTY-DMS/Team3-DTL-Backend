package com.example.dmstodo.exception;

import com.example.dmstodo.error.ErrorCode;

public class TokenInvalidException extends BusinessException{
    public TokenInvalidException(){
        super(ErrorCode.CANNOT_VALIDATE);
    }
}
