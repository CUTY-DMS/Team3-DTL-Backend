package com.example.dmstodo.controller.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter @Builder
public class FindOneTodoResDto {
    private final String title;
    private final String content;
    private final String userName;
}
