package com.example.dmstodo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Long userAge;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String userPw;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @OneToMany(mappedBy = "member")
    private List<Todo> todos = new ArrayList<>();

}
