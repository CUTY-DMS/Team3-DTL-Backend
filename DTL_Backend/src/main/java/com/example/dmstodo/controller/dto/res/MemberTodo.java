package com.example.dmstodo.controller.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Builder @Getter
public class MemberTodo {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDate createdAt;
    private final int likeCount;
    private final boolean isSuccess;
}
