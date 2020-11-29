package com.gate.planner.gate.model.dto.course.request.memo;

import com.gate.planner.gate.model.entity.course.memo.CourseMemo;
import com.gate.planner.gate.model.entity.course.memo.CourseMemoType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMemoDto {
    Long id;
    CourseMemoType type;
    String content;

    public CourseMemoDto(CourseMemo courseMemo) {
        this.id = courseMemo.getId();
        this.type = courseMemo.getType();
        this.content = courseMemo.getContent();
    }
}
