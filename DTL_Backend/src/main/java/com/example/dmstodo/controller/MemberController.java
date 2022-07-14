package com.example.dmstodo.controller;



import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.controller.dto.res.MemberResDto;
import com.example.dmstodo.controller.dto.req.MemberSignInDto;
import com.example.dmstodo.controller.dto.req.MemberSignUpDto;
import com.example.dmstodo.controller.dto.res.MyPageResDto;
import com.example.dmstodo.controller.dto.res.TokenResDto;
import com.example.dmstodo.exception.TokenInvalidException;
import com.example.dmstodo.service.MemberService;
import com.example.dmstodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;
    private final TodoService todoService;
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public MemberResDto signup(@Valid @RequestBody MemberSignUpDto req){
        return memberService.signup(req);
    }
    @PostMapping("/signin")
    public TokenResDto singIn(@RequestBody MemberSignInDto req){
        return memberService.login(req);
    }
    @GetMapping("/my")
    @ResponseBody
    public MyPageResDto myPage(){
        return memberService.findMember();
    }
    @GetMapping("/admin")
    public Object findAllUsers() {
        return memberService.findAllUsers();
    }

    @DeleteMapping("/my/{productId}")
    public String deleteTodo(@PathVariable Long productId){
        return todoService.deleteTodo(productId);
    }

    @PatchMapping("/my/{productId}")
    public void toSuccess(@PathVariable Long productId){
        todoService.toSuccess(productId);
    }
    @PutMapping("/my/{productId}")
    public String changeTodo(@PathVariable Long productId ,@RequestBody TodoReqDto req){
        return todoService.changeTodo(productId, req);
    }
}
