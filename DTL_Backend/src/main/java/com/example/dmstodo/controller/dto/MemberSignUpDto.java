package com.example.dmstodo.controller.dto;



import com.example.dmstodo.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@Builder
@AllArgsConstructor
public class MemberSignUpDto {
    @NotBlank
    @Size(max = 20)
    private String userId;
    @NotNull
    private Long userAge;
    @NotBlank @Size(max = 10)
    private String userName;
    @NotBlank
    @Size(min=8, max=20)
    private String userPw;

    private Role role;
}
