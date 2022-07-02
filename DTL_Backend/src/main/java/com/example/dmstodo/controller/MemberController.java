package com.example.dmstodo.controller;



import com.example.dmstodo.controller.dto.res.MemberResDto;
import com.example.dmstodo.controller.dto.req.MemberSignInDto;
import com.example.dmstodo.controller.dto.MemberSignUpDto;
import com.example.dmstodo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResDto signup(@Validated @RequestBody MemberSignUpDto req){
        System.out.println(req.getUserPw() + " " + req.getUserId());
        return memberService.signup(req);
    }
    @PostMapping("/signin")
    public MemberResDto singIn(@RequestBody MemberSignInDto req){
        return memberService.login(req);
    }
    @GetMapping("/my")
    public MemberSignUpDto myPage(Authentication authentication){
        if(authentication == null){
            throw new RuntimeException("cant find user");
        }
        return memberService.findMember(authentication.getName());
    }
}
