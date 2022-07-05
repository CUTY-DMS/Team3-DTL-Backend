package com.example.dmstodo.controller;


import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.domain.Todo;
import com.example.dmstodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    private void makeTodo(@RequestBody TodoReqDto req){
        todoService.makeTodo(req);
    }

    @GetMapping("/{id}")
    public Optional<Todo> getPost(@PathVariable Long id){
        return todoService.getPost(id);
    }
}
