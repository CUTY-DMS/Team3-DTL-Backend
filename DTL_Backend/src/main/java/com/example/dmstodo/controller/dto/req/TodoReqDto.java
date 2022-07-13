package com.example.dmstodo.controller.dto.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class TodoReqDto {
    @NotBlank @Size(min = 1, max = 20)
    private String title;
    @NotBlank @Size(min=1, max=100)
    private String content;
}
