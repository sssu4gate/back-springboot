package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.comment.request.CommentRequestDto;
import com.gate.planner.gate.model.dto.comment.response.CommentResponseDto;
import com.gate.planner.gate.service.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation("코스랑 관련된 댓글 전부 가져오기")
    @GetMapping("/get/{courseId}")
    public List<CommentResponseDto> getComment(@PathVariable Long courseId) { return commentService.getComment(courseId); }

    @ApiOperation("댓글 입력하기")
    @PostMapping("/")
    public void postComment(@RequestBody CommentRequestDto commentRequestDto){
        commentService.saveComment(commentRequestDto);
    }

    @ApiOperation("댓글 삭제")
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }

    @ApiOperation("댓글 수정")
    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody String content) {
        commentService.modifyComment(commentId, content);
    }
}
