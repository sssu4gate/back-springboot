package com.gate.planner.gate.model.dto.course.response;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseRequestType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CourseResponseDto {
    @ApiModelProperty("코스 식별자")
    Long id;
    @ApiModelProperty("코스 이름")
    String title;
    @ApiModelProperty("좋아요 수")
    int likeNum;
    @ApiModelProperty("댓글 수")
    int commentNum;

    @ApiModelProperty("작성 시간")
    Date createdAt;

    public CourseResponseDto(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.createdAt = course.getCreatedAt();
        this.likeNum = course.getLikeNum();
        this.commentNum = course.getCommentNum();
    }
}
