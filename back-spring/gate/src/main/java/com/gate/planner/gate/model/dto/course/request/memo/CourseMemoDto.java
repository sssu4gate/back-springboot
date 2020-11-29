package com.gate.planner.gate.model.dto.course.request.memo;

import com.gate.planner.gate.model.entity.course.memo.CourseMemo;
import com.gate.planner.gate.model.entity.course.memo.CourseMemoType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMemoDto {
    @ApiModelProperty("메모 식별자")
    Long id;
    @ApiModelProperty("메모 타입")
    CourseMemoType type;
    @ApiModelProperty("메모 본문")
    String content;

    public CourseMemoDto(CourseMemo courseMemo) {
        this.id = courseMemo.getId();
        this.type = courseMemo.getType();
        this.content = courseMemo.getContent();
    }
}
