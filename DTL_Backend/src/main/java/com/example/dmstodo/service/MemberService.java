package com.example.dmstodo.service;


import com.example.dmstodo.controller.dto.res.MemberResDto;
import com.example.dmstodo.controller.dto.req.MemberSignInDto;
import com.example.dmstodo.controller.dto.MemberSignUpDto;
import com.example.dmstodo.domain.Member;
import com.example.dmstodo.domain.MemberRepository;
import com.example.dmstodo.domain.Role;
import com.example.dmstodo.exception.UserAlreadyExistsExeption;
import com.example.dmstodo.exception.UserNotFoundException;
import com.example.dmstodo.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public MemberResDto signup(MemberSignUpDto req) {
        if(memberRepository.existsByUserId(req.getUserId())){
            throw new UserAlreadyExistsExeption();
        }
        memberRepository.save(Member.builder()
                .userName(req.getUserName())
                .userAge(req.getUserAge())
                .userId(req.getUserId())
                .userPw(passwordEncoder.encode(req.getUserPw()))
                .userRole(Role.USER)
                .build());
        return MemberResDto.builder()
                .message("회원가입 성공")
                .name(req.getUserName())
                .build();
    }
    public MemberResDto login(@Valid MemberSignInDto req) {
        Member member = memberRepository.findByUserId(req.getUserId())
                .orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(req.getUserPw(), member.getUserPw())) {
            throw new RuntimeException("wrong password");
        }
        return MemberResDto.builder()
                .message("로그인 성공")
                .name(jwtTokenProvider.createToken(member.getUserId(), List.of(member.getUserRole().getValue())))
                .build();
    }

    public MemberSignUpDto findMember(String uid){
        Member member = memberRepository.findByUserId(uid).orElseThrow(UserNotFoundException::new);
        return MemberSignUpDto.builder()
                .userId(member.getUserId())
                .userAge(member.getUserAge())
                .role(member.getUserRole())
                .userName(member.getUsername()).build();
    }
}
