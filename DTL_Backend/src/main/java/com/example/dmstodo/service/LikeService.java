package com.example.dmstodo.service;

import com.example.dmstodo.domain.like.Heart;
import com.example.dmstodo.domain.like.HeartRepository;
import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.member.MemberRepository;
import com.example.dmstodo.domain.todo.ToDoRepostiory;
import com.example.dmstodo.domain.todo.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import com.example.dmstodo.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final ToDoRepostiory toDoRepostiory;
    public String liked(Long todoId, String uid){
        if(findHeartWithUserAndTodoId(uid, todoId).isPresent()){
            return unlike(todoId, uid);
        }
        return like(todoId, uid);
    }
    public String like(Long todoId, String uid){
        Heart heart = Heart.builder()
                .todoId(todoId)
                .member(memberRepository.findByUserId(uid).get())
                .build();
        heartRepository.save(heart);
        Todo todo = toDoRepostiory.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);
        todo.setLikeCount(todo.getLikeCount()+1);
        toDoRepostiory.save(todo);
        return "좋아요 표시 성공";
    }
    public String unlike(Long todoId, String uid){
        heartRepository.deleteById(findHeartWithUserAndTodoId(uid, todoId).get().getId());
        Todo todo = toDoRepostiory.findById(todoId)
                .orElseThrow(TodoNotFoundException::new);
        todo.setLikeCount(todo.getLikeCount()-1);
        toDoRepostiory.save(todo);
        return "좋아요 취소됨";
    }
    public Optional<Heart> findHeartWithUserAndTodoId(String uid, Long todoId){
        Member member = memberRepository.findByUserId(uid)
                .orElseThrow(UserNotFoundException::new);
        return heartRepository.findHeartByMemberAndTodoId(
                member, todoId
        );
    }
}
