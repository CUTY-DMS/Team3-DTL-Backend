package com.example.dmstodo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    USER_ALREADY_EXISTS(409, "User already exists"),
    USER_NOT_FOUND(404, "User not found");
    private final Integer status;
    private final String message;
}
