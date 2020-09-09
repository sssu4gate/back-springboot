package com.gate.planner.gate.model.dto.request.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceRequestDto {
    String courseName;
    String userName;
    PlaceDto[] places;
}
