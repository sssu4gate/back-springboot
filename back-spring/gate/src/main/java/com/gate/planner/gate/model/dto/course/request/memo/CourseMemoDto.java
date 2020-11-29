package com.gate.planner.gate.model.dto.course.request.memo;

import com.gate.planner.gate.model.entity.course.memo.CourseMemoType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMemoDto {
    CourseMemoType type;
    String content;
}
