package com.example.dmstodo.service;


import com.example.dmstodo.controller.MemberTodo;
import com.example.dmstodo.controller.dto.res.MemberResDto;
import com.example.dmstodo.controller.dto.req.MemberSignInDto;
import com.example.dmstodo.controller.dto.req.MemberSignUpDto;
import com.example.dmstodo.controller.dto.res.MyPageResDto;
import com.example.dmstodo.controller.dto.res.TokenResDto;
import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.member.MemberRepository;
import com.example.dmstodo.domain.member.Roles;
import com.example.dmstodo.exception.UserAlreadyExistsExeption;
import com.example.dmstodo.exception.UserNotFoundException;
import com.example.dmstodo.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
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
                .userRole(Roles.valueOf("ROLE_USER"))
                .build());
        log.info("sign up :" +req.getUserId());
        return MemberResDto.builder()
                .message("회원가입 성공")
                .name(req.getUserName())
                .build();
    }
    public TokenResDto login(MemberSignInDto req) {
        Member member = memberRepository.findByUserId(req.getUserId())
                .orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(req.getUserPw(), member.getUserPw())) {
            throw new RuntimeException("wrong password");
        }
        log.info("login :" + req.getUserId());
        String token = jwtTokenProvider.createToken(member.getUserId(), member.getUserRole());
        return TokenResDto.builder()
                .msg("로그인 성공")
                .token(token)
                .build();
    }

    public MyPageResDto findMember(String memberId) {
        Member member = memberRepository.findByUserId(memberId)
                .orElseThrow(UserNotFoundException::new);

        List<MemberTodo> memberTodos = member.getTodos().stream()
                .map(todo -> MemberTodo.builder()
                        .id(todo.getId())
                        .title(todo.getTitle())
                        .content(todo.getContents())
                        .createdAt(todo.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return MyPageResDto.builder()
                .userId(memberId)
                .userAge(member.getUserAge())
                .userName(member.getUserName())
                .todos(memberTodos)
                .build();
    }

    public Object findAllUsers() {
        return memberRepository.findAll();
    }
}
