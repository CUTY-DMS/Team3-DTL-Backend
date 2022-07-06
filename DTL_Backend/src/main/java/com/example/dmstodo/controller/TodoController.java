package com.example.dmstodo.controller;


import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.domain.Todo;
import com.example.dmstodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    private void makeTodo(@RequestBody TodoReqDto req, Principal principal){
        System.out.println(principal.getName());
        return todoService.makeTodo(req, principal.getName());
    }

    @GetMapping
    public Todo getPost(@RequestParam String todoName){
        return todoService.getPost(todoName);
    }
}
