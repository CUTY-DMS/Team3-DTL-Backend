package com.example.dmstodo.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    USER_ALREADY_EXISTS(409, "User already exists"),
    USER_NOT_FOUND(404, "User not found"),
    TODO_NOT_FOUND(404, "Todo not exists");
    private final Integer status;
    private final String message;
}
