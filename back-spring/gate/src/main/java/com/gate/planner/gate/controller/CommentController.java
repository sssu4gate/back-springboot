package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.comment.request.CommentRequestDto;
import com.gate.planner.gate.model.dto.comment.response.CommentResponseDto;
import com.gate.planner.gate.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 내보내기
    @GetMapping("/get")
    public List<CommentResponseDto> getComment() { return commentService.getComment(); }

    // 댓글 입력
    @PostMapping("/post")
    public void postComment(@RequestBody CommentRequestDto commentRequestDto){
        commentService.saveComment(commentRequestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }

    // 댓글 수정
    @PutMapping("/modify/{id}")
    public void modifyComment(@PathVariable Long id, String content) {
        commentService.modifyComment(id, content);
    }
}
