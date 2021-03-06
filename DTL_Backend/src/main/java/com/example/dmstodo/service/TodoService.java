package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.controller.dto.res.FindAllTodoRes;
import com.example.dmstodo.controller.dto.res.FindOneTodoResDto;
import com.example.dmstodo.controller.dto.res.TodoResDto;
import com.example.dmstodo.domain.like.HeartRepository;
import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.todo.ToDoRepostiory;
import com.example.dmstodo.domain.todo.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import com.example.dmstodo.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final ToDoRepostiory toDoRepostiory;
    private final HeartRepository heartRepository;
    private final UserFacade userFacade;
    public TodoResDto makeTodo(TodoReqDto req) {
        Member member = userFacade.currentUser();
        toDoRepostiory.save(
                Todo.builder()
                        .title(req.getTitle())
                        .contents(req.getContent())
                        .likeCount(0)
                        .member(member)
                        .createdAt(LocalDate.now())
                        .isSuccess(false)
                        .build()
        );
        log.info("todo 추가: " + req.getTitle());
        return TodoResDto.builder()
                .msg("투두 추가 성공")
                .title(req.getTitle())
                .build();
    }

    @Transactional(readOnly = true)
    public List<FindAllTodoRes> getAllPosts(){
        return toDoRepostiory.findAllByOrderByIdDesc()
                .stream().map(a -> FindAllTodoRes.builder()
                        .id(a.getId())
                        .title(a.getTitle())
                        .content(a.getContents())
                        .createdAt(a.getCreatedAt())
                        .todoSuccess(a.isSuccess())
                        .likeCount(a.getLikeCount())
                        .memberId(a.getMember().getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    public String deleteTodo(Long id){
        toDoRepostiory.deleteById(id);
        return "투두 삭제 성공";
    }

    @Transactional(readOnly = true)
    public FindOneTodoResDto getTodo(Long id){
        Member member = userFacade.currentUser();
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        return FindOneTodoResDto.builder()
                .title(todo.getTitle())
                .content(todo.getContents())
                .userName(todo.getMember().getUserName())
                .createdAt(todo.getCreatedAt())
                .likeCount(todo.getLikeCount())
                .isLiked(heartRepository.existsByMemberAndTodoId(member, id))
                .isSuccess(todo.isSuccess())
                .build();
    }

    public void toSuccess(Long id){
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        todo.toSuccess();
        toDoRepostiory.save(todo);
    }

    public String changeTodo(Long id, TodoReqDto req){
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        todo.changeTodo(req.getTitle(), req.getContent());
        toDoRepostiory.save(todo);
        return "todo 변경 완료";
    }
}