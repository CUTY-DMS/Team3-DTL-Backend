package com.example.dmstodo.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Builder @Getter
public class MemberTodo {
    private final String title;
    private final String content;
    private final LocalDate createdAt;
}
