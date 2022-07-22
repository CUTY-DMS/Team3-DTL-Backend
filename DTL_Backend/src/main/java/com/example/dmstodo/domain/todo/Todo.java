package com.example.dmstodo.domain.todo;

import com.example.dmstodo.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    private String title;

    private LocalDate createdAt;

    private boolean isSuccess;

    private int likeCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void addLikeCount() {
        this.likeCount += 1;
    }
    public void removeLikeCount() {
        this.likeCount -= 1;
    }
}
