package com.example.dmstodo.controller.dto.res;

import com.example.dmstodo.controller.MemberTodo;
import com.example.dmstodo.domain.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Builder @Getter
public class MyPageResDto {
    private final String userName;
    private final String userId;
    private final int userAge;

    private final List<MemberTodo> todos;
}
