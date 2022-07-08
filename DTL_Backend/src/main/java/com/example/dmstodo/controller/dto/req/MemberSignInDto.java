package com.example.dmstodo.controller.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class MemberSignInDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPw;
}
