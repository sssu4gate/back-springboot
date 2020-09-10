package com.gate.planner.gate.model.dto.request.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriteCourseRequestDto {
    String courseName;
    String userName;
    PlacePostDto[] places;
}
