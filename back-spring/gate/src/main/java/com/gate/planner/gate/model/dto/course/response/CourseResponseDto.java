package com.gate.planner.gate.model.dto.course.response;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseRequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CourseResponseDto {
    Long id;
    String title;
    int likeNum;
    int commentNum;

    public CourseResponseDto(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.likeNum = course.getLikeNum();
        this.commentNum = course.getCommentNum();
    }
}
