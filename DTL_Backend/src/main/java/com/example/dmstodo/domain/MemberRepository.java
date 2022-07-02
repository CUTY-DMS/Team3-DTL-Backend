package com.example.dmstodo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUserId(String uid);

    Optional<Member> findByUserId(String uid);
}
