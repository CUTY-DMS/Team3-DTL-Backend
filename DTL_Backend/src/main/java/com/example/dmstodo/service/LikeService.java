package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.res.TodoLikeResDto;
import com.example.dmstodo.domain.like.Heart;
import com.example.dmstodo.domain.like.HeartRepository;
import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.member.MemberRepository;
import com.example.dmstodo.domain.todo.ToDoRepostiory;
import com.example.dmstodo.domain.todo.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import com.example.dmstodo.exception.UserNotFoundException;
import com.example.dmstodo.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final ToDoRepostiory toDoRepostiory;
    private final UserFacade userFacade;
    public TodoLikeResDto liked(Long todoId){
        Member member = userFacade.currentUser();
        if(findHeartWithUserAndTodoId(member.getUserId(), todoId).isPresent()){
            return unlike(todoId, member.getUserId());
        }
        return like(todoId, member.getUserId());
    }
    public TodoLikeResDto like(Long todoId, String uid){
        Heart heart = Heart.builder()
                .todoId(todoId)
                .member(memberRepository.findByUserId(uid).get())
                .build();
        heartRepository.save(heart);
        Todo todo = toDoRepostiory.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);
        todo.setLikeCount(todo.getLikeCount()+1);
        toDoRepostiory.save(todo);
        return TodoLikeResDto.builder()
                .likeCount(todo.getLikeCount())
                .isLiked(findHeartWithUserAndTodoId(uid, todoId).isPresent())
                .build();
    }
    public TodoLikeResDto unlike(Long todoId, String uid){
        heartRepository.deleteById(findHeartWithUserAndTodoId(uid, todoId).get().getId());
        Todo todo = toDoRepostiory.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);
        todo.setLikeCount(todo.getLikeCount()-1);
        toDoRepostiory.save(todo);
        return TodoLikeResDto.builder()
                .likeCount(todo.getLikeCount())
                .isLiked(findHeartWithUserAndTodoId(uid, todoId).isPresent())
                .build();
    }
    public Optional<Heart> findHeartWithUserAndTodoId(String uid, Long todoId){
        Member member = memberRepository.findByUserId(uid)
                .orElseThrow(UserNotFoundException::new);
        return heartRepository.findHeartByMemberAndTodoId(
                member, todoId
        );
    }
}
