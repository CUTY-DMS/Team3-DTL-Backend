package com.example.dmstodo.domain.member;

import com.example.dmstodo.domain.todo.Todo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private int userAge;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String userPw;

    @Enumerated(EnumType.STRING)
    private Roles userRole;

    @OneToMany(mappedBy = "member")
    private List<Todo> todos = new ArrayList<>();

}
