package com.gate.planner.gate.model.dto.comment.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel
@Getter
public class CommentRequestDto {

    @ApiModelProperty("댓글 본문")
    String content;
    @ApiModelProperty("코스의 식별자")
    Long courseId;

    public CommentRequestDto(String content, Long courseId) {
        this.content = content;
        this.courseId = courseId;
    }
}