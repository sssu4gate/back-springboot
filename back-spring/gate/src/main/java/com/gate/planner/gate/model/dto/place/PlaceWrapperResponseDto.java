package com.gate.planner.gate.model.dto.place;

import com.gate.planner.gate.dao.place.PlaceWrapperRepository;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceWrapperResponseDto {
    int cost;
    String time;
    PlaceDto placeDto;

    public PlaceWrapperResponseDto(PlaceWrapper placeWrapper) {
        this.cost = placeWrapper.getCost();
        this.placeDto = new PlaceDto(placeWrapper.getPlace());
        this.time = placeWrapper.getTime();
    }

}
