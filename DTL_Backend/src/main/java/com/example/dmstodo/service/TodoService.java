package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.controller.dto.res.TodoResDto;
import com.example.dmstodo.domain.MemberRepository;
import com.example.dmstodo.domain.ToDoRepostiory;
import com.example.dmstodo.domain.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepostiory toDoRepostiory;
    private final MemberRepository memberRepository;

    public TodoResDto makeTodo(TodoReqDto req, String userId) {
        toDoRepostiory.save(
                Todo.builder()
                        .title(req.getTitle())
                        .contents(req.getContent())
                        .member(memberRepository.findByUserId(userId).orElseThrow(
                                RuntimeException::new
                        ))
                        .build()
        );
        return TodoResDto.builder()
                .msg("Todo이름 : " + req.getTitle() + "db추가 완료")
                .build();
    }

    public Todo getPost(String todoId) {
        return toDoRepostiory.findByTitle(todoId).orElseThrow(TodoNotFoundException::new);
    }

    public Object getAllPosts(){
        return toDoRepostiory.findAll();
    }
}