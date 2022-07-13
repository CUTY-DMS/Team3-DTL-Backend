package com.example.dmstodo.facade;

import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.member.MemberRepository;
import com.example.dmstodo.exception.TokenInvalidException;
import com.example.dmstodo.exception.UserNotFoundException;
import com.example.dmstodo.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final MemberRepository memberRepository;
    public Member currentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof CustomUserDetails)) {
            throw new TokenInvalidException();
        }
        return memberRepository.findByUserId(((CustomUserDetails) principal).getUsername())
                .orElseThrow(UserNotFoundException :: new);
    }
}
