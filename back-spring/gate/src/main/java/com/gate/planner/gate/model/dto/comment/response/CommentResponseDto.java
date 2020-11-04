package com.gate.planner.gate.model.dto.comment.response;

import com.gate.planner.gate.model.entity.comment.Comment;

import java.util.Date;
import java.util.List;

public class CommentResponseDto {

    private String content;

    private String nickName;

    private Date createdAt;

    private List<Comment> childComment;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.nickName = comment.getUser().getNickName();
        this.createdAt = comment.getCreatedAt();
        this.childComment = comment.getChildComment();
    }
}
