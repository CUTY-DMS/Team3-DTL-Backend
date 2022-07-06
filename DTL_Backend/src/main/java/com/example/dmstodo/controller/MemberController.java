package com.example.dmstodo.controller;



import com.example.dmstodo.controller.dto.res.MemberResDto;
import com.example.dmstodo.controller.dto.req.MemberSignInDto;
import com.example.dmstodo.controller.dto.req.MemberSignUpDto;
import com.example.dmstodo.domain.Member;
import com.example.dmstodo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public MemberResDto signup(@Validated @RequestBody MemberSignUpDto req){
        System.out.println(req.getUserPw() + " " + req.getUserId());
        return memberService.signup(req);
    }
    @PostMapping("/signin")
    public MemberResDto singIn(@RequestBody MemberSignInDto req){
        return memberService.login(req);
    }
    @GetMapping("/my")
    @ResponseBody
    public Optional<Member> myPage(Principal principal){
        return memberService.findMember(principal.getName());
    }

}
