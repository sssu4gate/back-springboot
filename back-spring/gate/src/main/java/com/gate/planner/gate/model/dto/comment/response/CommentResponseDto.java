package com.gate.planner.gate.model.dto.comment.response;

import com.gate.planner.gate.model.entity.comment.Comment;
import com.gate.planner.gate.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel
public class CommentResponseDto {

    @ApiModelProperty("댓글 본문")
    private String content;

    @ApiModelProperty("댓글 작성자")
    private String nickName;

    @ApiModelProperty("댓글 작성 시간")
    private String createdAt;

    @ApiModelProperty("대 댓글")
    private List<Comment> childComment;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.nickName = comment.getUser().getNickName();
        this.createdAt = DateUtil.parseString(comment.getCreatedAt());
        this.childComment = comment.getChildComment();
    }
}
