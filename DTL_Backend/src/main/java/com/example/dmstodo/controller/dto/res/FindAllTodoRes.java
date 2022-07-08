package com.example.dmstodo.controller.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@Builder @Getter
public class FindAllTodoRes {
    private final Long id;
    private final String contents;
    private final String title;
    private final LocalDate createdAt;
    private final String memberId;
}
