package com.example.dmstodo.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    USER_ALREADY_EXISTS(409, "User already exists"),
    USER_NOT_FOUND(404, "User not found"),
    TODO_NOT_FOUND(404, "Todo not exists"),
    CANNOT_ACCESS(403, "변경 권한이 없습니다."),
    CANNOT_VALIDATE(404, "토큰이 유효하지 않거나 없습니다."),
    ALREADY_LIKED(409, "이미 좋아요를 눌렀습니다.")

    ;
    private final Integer status;
    private final String message;
}
