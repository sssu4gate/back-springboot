package com.gate.planner.gate.model.dto.course.request;

import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {
    String courseName;
    String content;
    List<PlaceWrapperDto> places;
    List<String> memos;

}
