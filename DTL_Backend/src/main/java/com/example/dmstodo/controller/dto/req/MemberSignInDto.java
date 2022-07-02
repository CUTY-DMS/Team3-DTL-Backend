package com.example.dmstodo.controller.dto.req;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class MemberSignInDto {
    @NotBlank
    private final String userId;
    @NotBlank
    private final String userPw;
}
