package com.gate.planner.gate.model.dto.course.response;

import com.gate.planner.gate.model.dto.course.request.memo.CourseMemoDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseShareType;
import com.gate.planner.gate.model.entity.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
@Getter
@NoArgsConstructor
public class CourseResponseDetailDto {

    @ApiModelProperty("코스 식별자")
    Long id;

    @ApiModelProperty("코스 이름")
    String title;

    @ApiModelProperty("코스 본문")
    String content;

    @ApiModelProperty("코스 작성자 닉네임")
    String nickName;

    @ApiModelProperty("데이트 날짜")
    Date Dday;

    @ApiModelProperty("댓글 수")
    @Setter
    int commentNum = 0;

    @ApiModelProperty("좋아요 수")
    @Setter
    int likeNum = 0;

    @ApiModelProperty("데이트 총액")
    int totalCost;

    @ApiModelProperty("장소들")
    List<PlaceWrapperResponseDto> places;
    @ApiModelProperty("체크 리스트")
    List<CourseMemoDto> memos;

    @ApiModelProperty("작성 시간")
    Date createdAt;

    @ApiModelProperty("공유 범위")
    CourseShareType shareType;

    public CourseResponseDetailDto(Course course, User user) {
        this.id = course.getId();
        this.likeNum = course.getLikeNum();
        this.commentNum = course.getCommentNum();
        this.nickName = user.getNickName();
        this.createdAt = course.getCreatedAt();
        this.Dday = course.getDday();
        this.shareType = course.getShareType();
        this.title = course.getTitle();
        this.content = course.getContent();
        this.totalCost = course.getTotalCost();
        this.memos = course.getMemos().stream().map(CourseMemoDto::new).collect(Collectors.toList());
        this.places = course.getPlaces().stream().map(PlaceWrapperResponseDto::new).collect(Collectors.toList());

    }
}
