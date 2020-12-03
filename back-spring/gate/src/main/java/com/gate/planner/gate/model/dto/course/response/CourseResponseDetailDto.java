package com.gate.planner.gate.model.dto.course.response;

import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.ShareType;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
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

    @ApiModelProperty
    String userImgUrl;

    @ApiModelProperty("코스 대표 이미지")
    String courseImgUrl;

    @ApiModelProperty("코스 작성자 닉네임")
    String nickName;

    @ApiModelProperty("데이트 날짜")
    String dateDay;

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
    List<CourseMemoResponseDto> memos;

    @ApiModelProperty("작성 시간")
    String createdAt;

    @ApiModelProperty("공유 범위")
    ShareType shareType;

    @Builder
    public CourseResponseDetailDto(Long id, String courseImgUrl, String userImgUrl, int likeNum, int commentNum, String nickName, Date createdAt, Date dateDay, ShareType type, String title, String content, int totalCost, List<PlaceWrapperResponseDto> places, List<CourseMemoResponseDto> memos) {
        this.id = id;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.courseImgUrl = courseImgUrl;
        this.userImgUrl = userImgUrl;
        this.nickName = nickName;
        this.createdAt = DateUtil.parseString(createdAt);
        this.dateDay = DateUtil.parseString(dateDay);
        this.shareType = type;
        this.title = title;
        this.content = content;
        this.totalCost = totalCost;
        this.places = places;
        this.memos = memos;
    }

    public CourseResponseDetailDto(Course course, User user) {
        this.id = course.getId();
        this.likeNum = course.getLikeNum();
        this.userImgUrl = user.getImgUrl();
        this.courseImgUrl = course.getImgUrl();
        this.commentNum = course.getCommentNum();
        this.nickName = user.getNickName();
        this.createdAt = DateUtil.parseString(course.getCreatedAt());
        this.dateDay = DateUtil.parseString(course.getDateDay());
        this.shareType = course.getShareType();
        this.title = course.getTitle();
        this.content = course.getContent();
        this.totalCost = course.getTotalCost();
        this.memos = course.getMemos() == null ? null : course.getMemos().stream().map(CourseMemoResponseDto::new).collect(Collectors.toList());
        this.places = course.getPlaces() == null ? null : course.getPlaces().stream().map(PlaceWrapperResponseDto::new).collect(Collectors.toList());

    }
}
