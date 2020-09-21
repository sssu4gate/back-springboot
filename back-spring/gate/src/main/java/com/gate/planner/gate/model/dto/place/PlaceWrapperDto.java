package com.gate.planner.gate.model.dto.place;

import lombok.Getter;

@Getter
public class PlaceWrapperDto {

    //place의 id
    Long id;
    //place의 cost
    int cost;
    //place에 있었던 시간
    String time;
}
