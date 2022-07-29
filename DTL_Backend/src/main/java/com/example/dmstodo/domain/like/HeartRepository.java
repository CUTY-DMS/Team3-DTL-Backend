package com.example.dmstodo.domain.like;

import com.example.dmstodo.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findHeartByMemberAndTodoId(Member member, Long todoId);

    boolean existsByMemberAndTodoId(Member member, Long todoId);

    void deleteByMemberAndTodoId(Member member, Long todoId);
}
