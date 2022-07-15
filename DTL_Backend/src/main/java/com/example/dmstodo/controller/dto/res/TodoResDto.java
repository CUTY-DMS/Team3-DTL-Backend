package com.example.dmstodo.controller.dto.res;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder @Getter
public class TodoResDto {
    private final String msg;
    private final String title;
}
