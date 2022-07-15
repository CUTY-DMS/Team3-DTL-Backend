package com.example.dmstodo.controller.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder @Getter
public class TodoLikeResDto {
    private final int likeCount;
    private final boolean isLiked;
}
