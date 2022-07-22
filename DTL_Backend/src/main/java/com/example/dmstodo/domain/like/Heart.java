package com.example.dmstodo.domain.like;

import com.example.dmstodo.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long todoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
