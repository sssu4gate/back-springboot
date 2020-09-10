package com.gate.planner.gate.model.dto.request.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlacePostDto {
    String address;
    String placeName;
    String content;
    int imgCount;
}
