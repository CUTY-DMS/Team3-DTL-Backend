package com.example.dmstodo.exception;


import com.example.dmstodo.error.ErrorCode;

public class AccessDeniedException extends BusinessException{
    public AccessDeniedException(){
        super(ErrorCode.CANNOT_ACCESS);
    }
}
