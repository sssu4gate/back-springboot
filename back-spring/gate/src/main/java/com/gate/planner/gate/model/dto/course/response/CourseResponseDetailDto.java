package com.gate.planner.gate.model.dto.course.response;

import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

    @ApiModelProperty("코스 작성자")
    String userName;

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
    List<String> memos;

    @ApiModelProperty("작성 시간")
    Date createdAt;

    @Builder
    public CourseResponseDetailDto(Long id, Date createdAt, String title, String content, String userName, int totalCost, List<PlaceWrapperResponseDto> places, List<String> memos) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.totalCost = totalCost;
        this.places = places;
        this.memos = memos;
    }
}
