package com.example.dmstodo.service;

import com.example.dmstodo.controller.dto.req.TodoReqDto;
import com.example.dmstodo.controller.dto.res.FindAllTodoRes;
import com.example.dmstodo.controller.dto.res.FindOneTodoResDto;
import com.example.dmstodo.controller.dto.res.TodoResDto;
import com.example.dmstodo.domain.like.HeartRepository;
import com.example.dmstodo.domain.member.Member;
import com.example.dmstodo.domain.member.MemberRepository;
import com.example.dmstodo.domain.todo.ToDoRepostiory;
import com.example.dmstodo.domain.todo.Todo;
import com.example.dmstodo.exception.TodoNotFoundException;
import com.example.dmstodo.exception.UserNotFoundException;
import com.example.dmstodo.facade.UserFacade;
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
    private final HeartRepository heartRepository;
    private final UserFacade userFacade;
    public TodoResDto makeTodo(TodoReqDto req) {
        Member member = userFacade.currentUser();
        toDoRepostiory.save(
                Todo.builder()
                        .title(req.getTitle())
                        .contents(req.getContent())
                        .likeCount(0)
                        .member(memberRepository.findByUserId(member.getUserId()).orElseThrow(
                                UserNotFoundException::new
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
        /*String uid = currentUserId();*/
        return toDoRepostiory.findAllByOrderByIdDesc()
                .stream().map(a -> FindAllTodoRes.builder()
                        .id(a.getId())
                        /*.isLiked(heartRepository.findHeartByMemberAndTodoId(
                                memberRepository.findByUserId(uid)
                                        .orElseThrow(UserNotFoundException::new),
                                a.getId()).isPresent())*/
                        .title(a.getTitle())
                        .content(a.getContents())
                        .createdAt(a.getCreatedAt())
                        .isSuccess(a.isSuccess())
                        .likeCount(a.getLikeCount())
                        .memberId(a.getMember().getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    public String deleteTodo(Long id){
        String res = "todo삭제 완료 ";
        toDoRepostiory.deleteById(id);
        return res;
    }

    public FindOneTodoResDto getTodo(Long id){
        Member member = userFacade.currentUser();
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        return FindOneTodoResDto.builder()
                .title(todo.getTitle())
                .content(todo.getContents())
                .userName(member.getUserName())
                .createdAt(todo.getCreatedAt())
                .likeCount(todo.getLikeCount())
                .isLiked(heartRepository.findHeartByMemberAndTodoId(
                        memberRepository.findByUserId(member.getUserId())
                                .orElseThrow(UserNotFoundException::new)
                        , id).isPresent())
                .isSuccess(todo.isSuccess())
                .build();
    }

    public void toSuccess(Long id){
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        todo.setSuccess(!todo.isSuccess());
        toDoRepostiory.save(todo);
    }

    public String changeTodo(Long id, TodoReqDto req){
        Todo todo = toDoRepostiory.findById(id)
                .orElseThrow(TodoNotFoundException :: new);
        todo.setTitle(req.getTitle());
        todo.setContents(req.getContent());
        toDoRepostiory.save(todo);
        return "todo 변경 완료";
    }
}