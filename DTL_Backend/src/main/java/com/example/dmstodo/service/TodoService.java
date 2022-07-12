package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.controller.dto.res.FindAllTodoRes;
import com.example.dmstodo.controller.dto.res.FindOneTodoResDto;
import com.example.dmstodo.controller.dto.res.TodoResDto;
import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.member.MemberRepository;
import com.example.dmstodo.domain.todo.ToDoRepostiory;
import com.example.dmstodo.domain.todo.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import com.example.dmstodo.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepostiory toDoRepostiory;
    private final MemberRepository memberRepository;
    public TodoResDto makeTodo(TodoReqDto req, String userId) {
        toDoRepostiory.save(
                Todo.builder()
                        .title(req.getTitle())
                        .contents(req.getContents())
                        .member(memberRepository.findByUserId(userId).orElseThrow(
                                RuntimeException::new
                        ))
                        .createdAt(LocalDate.now())
                        .isSuccess(false)
                        .build()
        );
        log.info("todo 추가: " + req.getTitle());
        return TodoResDto.builder()
                .msg("Todo이름 : " + req.getTitle() + "db추가 완료")
                .build();
    }

    public List<FindAllTodoRes> getAllPosts(){
        return toDoRepostiory.findAllByOrderByIdDesc()
                .stream().map(a -> FindAllTodoRes.builder()
                        .id(a.getId())
                        .title(a.getTitle())
                        .contents(a.getContents())
                        .createdAt(a.getCreatedAt())
                        .isSuccess(a.isSuccess())
                        .memberId(a.getMember().getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    public String deleteTodo(Long id){
        String res = "todo삭제 완료 : " + toDoRepostiory.findById(id).get().getTitle();
        toDoRepostiory.deleteById(id);
        return res;
    }

    public FindOneTodoResDto getTodo(Long id){
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        Member member = memberRepository.findByUserId(todo.getMember().getUserId())
                .orElseThrow(UserNotFoundException::new);
        return FindOneTodoResDto.builder()
                .title(todo.getTitle())
                .content(todo.getContents())
                .userName(member.getUserName())
                .build();
    }

    public void toSuccess(Long id){
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        todo.setSuccess(true);
        toDoRepostiory.save(todo);
    }
}