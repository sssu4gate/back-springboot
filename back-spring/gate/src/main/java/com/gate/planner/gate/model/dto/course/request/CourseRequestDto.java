package com.gate.planner.gate.model.dto.course.request;

import com.gate.planner.gate.model.dto.course.request.memo.CourseMemoDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import com.gate.planner.gate.model.entity.course.CourseShareType;
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
    @ApiModelProperty("장소들")
    List<PlaceWrapperDto> places;
    @ApiModelProperty("체크 리스트")
    List<CourseMemoDto> memos;
    @ApiModelProperty("공유 범위")
    CourseShareType shareType;

}
