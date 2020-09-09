package com.gate.planner.gate.model.dto.request.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceDto {
    //경도
    String longitude;
    //위도
    String latitude;
    String name;
}
