package com.example.dmstodo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoRepostiory extends JpaRepository<Todo, Long> {
    Optional<Todo> findByTitle(String todoName);
    List<Todo> findAllByOrderByIdDesc();
}
