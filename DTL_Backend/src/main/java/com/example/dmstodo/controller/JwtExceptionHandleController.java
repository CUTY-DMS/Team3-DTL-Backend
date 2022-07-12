package com.example.dmstodo.controller;

import com.example.dmstodo.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class JwtExceptionHandleController {
    @GetMapping("/jwt")
    public void JwtException(){
        throw new UserNotFoundException();
    }
}
