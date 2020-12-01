package com.gate.planner.gate.model.dto.course.request;

import com.gate.planner.gate.model.dto.place.PlaceWrapperRequestDto;
import com.gate.planner.gate.model.entity.course.ShareType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {
    @ApiModelProperty("코스 이름")
    String courseName;
    @ApiModelProperty("코스 본문")
    String content;
    @ApiModelProperty("데이트 날짜")
    String dateDay;
    @ApiModelProperty("장소들")
    List<PlaceWrapperRequestDto> places;
    @ApiModelProperty("체크 리스트")
    List<CourseMemoRequestDto> memos;
    @ApiModelProperty("공유 범위")
    ShareType shareType;

}
