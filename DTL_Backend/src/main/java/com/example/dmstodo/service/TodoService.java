package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.domain.MemberRepository;
import com.example.dmstodo.domain.ToDoRepostiory;
import com.example.dmstodo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepostiory toDoRepostiory;
    private final MemberRepository memberRepository;

    public void makeTodo(TodoReqDto req){
        toDoRepostiory.save(
                Todo.builder()
                        .title(req.getTitle())
                        .contents(req.getContent())
                        .build()
        );
    }

    public Optional<Todo> getPost(Long id){
        return toDoRepostiory.findById(id);
    }
}
