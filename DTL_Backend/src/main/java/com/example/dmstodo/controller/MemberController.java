package com.example.dmstodo.controller;



import com.example.dmstodo.controller.dto.res.MemberResDto;
import com.example.dmstodo.controller.dto.req.MemberSignInDto;
import com.example.dmstodo.controller.dto.req.MemberSignUpDto;
import com.example.dmstodo.controller.dto.res.MyPageResDto;
import com.example.dmstodo.controller.dto.res.TokenResDto;
import com.example.dmstodo.service.MemberService;
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
    public MyPageResDto myPage(Principal principal){
        return memberService.findMember(principal.getName());
    }
    @GetMapping("/admin")
    public Object findAllUsers() {
        return memberService.findAllUsers();
    }
}
