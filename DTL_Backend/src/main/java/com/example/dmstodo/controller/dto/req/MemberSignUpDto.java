package com.example.dmstodo.controller.dto.req;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@Builder
@AllArgsConstructor
public class MemberSignUpDto {

    @NotBlank
    @Size(max = 20)
    private String userId;

    @NotNull
    @Min(value = 1)
    @Max(value = 120)
    private int userAge;

    @NotBlank
    @Size(max = 10)
    private String userName;

    @NotBlank
    @Size(min=8, max=20)
    private String userPw;
}
