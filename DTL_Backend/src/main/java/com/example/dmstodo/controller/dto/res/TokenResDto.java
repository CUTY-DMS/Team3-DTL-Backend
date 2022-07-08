package com.example.dmstodo.controller.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder @Getter
public class TokenResDto {
    private String msg;
    private String token;
}
