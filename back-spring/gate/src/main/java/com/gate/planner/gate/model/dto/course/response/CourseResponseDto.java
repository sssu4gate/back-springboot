package com.gate.planner.gate.model.dto.course.response;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.ShareType;
import com.gate.planner.gate.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CourseResponseDto {
    @ApiModelProperty("코스 식별자")
    Long id;
    @ApiModelProperty("코스 이름")
    String title;

    @ApiModelProperty("유저 닉네임")
    String nickName;

    @ApiModelProperty("코스 본문")
    String content;

    @ApiModelProperty("코스 이미지")
    String courseImgUrl;

    @ApiModelProperty("유저 이미지")
    String userImgUrl;

    @ApiModelProperty("좋아요 수")
    int likeNum;
    @ApiModelProperty("댓글 수")
    int commentNum;

    @ApiModelProperty("작성 시간")
    String createdAt;

    @ApiModelProperty("공유 범위")
    ShareType shareType;

    public CourseResponseDto(Course course) {
        this.id = course.getId();
        this.shareType = course.getShareType();
        this.courseImgUrl = course.getImgUrl();
        this.nickName = course.getUser().getNickName();
        this.content = course.getContent();
        this.userImgUrl = course.getUser().getImgUrl();
        this.title = course.getTitle();
        this.createdAt = DateUtil.parseString(course.getCreatedAt());
        this.likeNum = course.getLikeNum();
        this.commentNum = course.getCommentNum();
    }
}
