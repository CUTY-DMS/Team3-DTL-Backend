package com.example.dmstodo.controller;


import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.controller.dto.res.FindAllTodoRes;
import com.example.dmstodo.controller.dto.res.FindOneTodoResDto;
import com.example.dmstodo.controller.dto.res.TodoResDto;
import com.example.dmstodo.domain.todo.Todo;
import com.example.dmstodo.exception.TokenInvalidException;
import com.example.dmstodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    private TodoResDto makeTodo(@Valid @RequestBody TodoReqDto req){
        return todoService.makeTodo(req);
    }
    @GetMapping("/main")
    public List<FindAllTodoRes> getAllPosts(){
        return todoService.getAllPosts();
    }
    @GetMapping("/{todoId}")
    public FindOneTodoResDto getTodo(@PathVariable Long todoId){
        return todoService.getTodo(todoId);
    }
}
