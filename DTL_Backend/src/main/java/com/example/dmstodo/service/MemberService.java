package com.example.dmstodo.service;


import com.example.dmstodo.controller.dto.res.MemberResDto;
import com.example.dmstodo.controller.dto.req.MemberSignInDto;
import com.example.dmstodo.controller.dto.req.MemberSignUpDto;
import com.example.dmstodo.controller.dto.res.MyPageResDto;
import com.example.dmstodo.controller.dto.res.TokenResDto;
import com.example.dmstodo.domain.Member;
import com.example.dmstodo.domain.MemberRepository;
import com.example.dmstodo.domain.Roles;
import com.example.dmstodo.exception.UserAlreadyExistsExeption;
import com.example.dmstodo.exception.UserNotFoundException;
import com.example.dmstodo.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .userRole(Roles.valueOf("ROLE_USER"))
                .build());
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
        String token = jwtTokenProvider.createToken(member.getUserId(), member.getUserRole());
        return TokenResDto.builder()
                .msg("로그인 성공")
                .token(token)
                .build();
    }

    public MyPageResDto findMember(String memberId) {
        return memberRepository.findByUserId(memberId)
                .map(a -> MyPageResDto.builder()
                        .userName(a.getUserName())
                        .userAge(a.getUserAge())
                        .userId(a.getUserId())
//                        .todos(getTodo(a.getTodos()))
                        .build())
                .orElseThrow(UserNotFoundException :: new);
    }

    public Object findAllUsers() {
        return memberRepository.findAll();
    }
}
