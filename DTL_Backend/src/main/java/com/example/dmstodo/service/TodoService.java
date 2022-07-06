package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.domain.MemberRepository;
import com.example.dmstodo.domain.ToDoRepostiory;
import com.example.dmstodo.domain.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepostiory toDoRepostiory;
    private final MemberRepository memberRepository;

    public void makeTodo(TodoReqDto req, String userId) {
        toDoRepostiory.save(
                Todo.builder()
                        .title(req.getTitle())
                        .contents(req.getContent())
                        .member(memberRepository.findByUserId(userId).orElseThrow(
                                RuntimeException::new
                        ))
                        .build()
        );
    }

    public Todo getPost(String todoId) {
        return toDoRepostiory.findByTitle(todoId).orElseThrow(TodoNotFoundException::new);
    }
}