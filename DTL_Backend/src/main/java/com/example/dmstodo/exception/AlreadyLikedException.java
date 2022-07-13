package com.example.dmstodo.exception;

import com.example.dmstodo.error.ErrorCode;

public class AlreadyLikedException extends BusinessException{
    public AlreadyLikedException(){
        super(ErrorCode.ALREADY_LIKED);
    }
}
