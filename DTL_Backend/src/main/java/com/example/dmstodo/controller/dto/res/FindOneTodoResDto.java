package com.example.dmstodo.controller.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter @Builder
public class FindOneTodoResDto {
    private final String title;
    private final String content;
    private final String userName;
    private final boolean isSuccess;
    private final int likeCount;
    private final boolean isLiked;
    private final LocalDate createdAt;
}
