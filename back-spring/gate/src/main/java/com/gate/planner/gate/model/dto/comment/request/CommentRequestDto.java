package com.gate.planner.gate.model.dto.comment.request;

import com.gate.planner.gate.model.entity.course.Course;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    String content;
    Course course;

    public CommentRequestDto(String content, Course course){
        this.content = content;
        this.course = course;
    }
}
