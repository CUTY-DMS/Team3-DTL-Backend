package com.example.dmstodo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepostiory extends JpaRepository<Todo, Long> {
}
