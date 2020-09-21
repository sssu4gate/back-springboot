package com.gate.planner.gate.model.dto.course.request;

import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {
    String courseName;
    String content;
    List<PlaceWrapperDto> places;

}
