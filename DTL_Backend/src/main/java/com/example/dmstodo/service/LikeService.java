package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.res.TodoLikeResDto;
import com.example.dmstodo.domain.like.Heart;
import com.example.dmstodo.domain.like.HeartRepository;
import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.todo.ToDoRepostiory;
import com.example.dmstodo.domain.todo.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import com.example.dmstodo.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final HeartRepository heartRepository;
    private final ToDoRepostiory toDoRepostiory;
    private final UserFacade userFacade;
    public TodoLikeResDto liked(Long todoId){
        Member member = userFacade.currentUser();
        if(heartRepository.existsByMemberAndTodoId(member, todoId)){
            return unlike(todoId, member);
        }
        return like(todoId, member);
    }
    public TodoLikeResDto like(Long todoId, Member member){
        heartRepository.save(Heart.builder()
                .todoId(todoId)
                .member(member)
                .build());
        Todo todo = toDoRepostiory.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);
        todo.addLikeCount();
        toDoRepostiory.save(todo);
        return TodoLikeResDto.builder()
                .likeCount(todo.getLikeCount())
                .isLiked(heartRepository.existsByMemberAndTodoId(member, todoId))
                .build();
    }
    public TodoLikeResDto unlike(Long todoId, Member member){
        heartRepository.deleteByMemberAndTodoId(member, todoId);
        Todo todo = toDoRepostiory.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);
        todo.removeLikeCount();
        toDoRepostiory.save(todo);
        return TodoLikeResDto.builder()
                .likeCount(todo.getLikeCount())
                .isLiked(heartRepository.existsByMemberAndTodoId(member, todoId))
                .build();
    }
}
