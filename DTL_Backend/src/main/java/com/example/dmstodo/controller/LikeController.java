package com.example.dmstodo.controller;

import com.example.dmstodo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/main/like")
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/{todoId}")
    public String liked(@PathVariable Long todoId, Principal principal){
        return likeService.liked(todoId, principal.getName());
    }
}
