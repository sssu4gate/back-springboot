package com.gate.planner.gate.model.dto.course.request;

import com.gate.planner.gate.model.entity.course.memo.CourseMemoType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMemoRequestDto {
    String content;
    CourseMemoType type;
}
