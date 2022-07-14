package com.example.dmstodo.controller;

import com.example.dmstodo.controller.dto.res.TodoLikeResDto;
import com.example.dmstodo.exception.TokenInvalidException;
import com.example.dmstodo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/main/like")
public class LikeController {
    private final LikeService likeService;
    @GetMapping("/{todoId}")
    public TodoLikeResDto liked(@PathVariable Long todoId){
        return likeService.liked(todoId);
    }
}
