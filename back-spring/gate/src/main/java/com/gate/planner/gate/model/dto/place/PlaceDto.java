package com.gate.planner.gate.model.dto.place;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gate.planner.gate.model.entity.place.Place;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDto {

    private Long id;
    private String address_name;
    private String place_name;
    private String category_name;
    private String x;
    private String y;


    public PlaceDto(Place place) {
        this.id = place.getId();
        this.address_name = place.getAddress();
        this.category_name = place.getCategory();
        this.x = place.getCoordinate().getX();
        this.y = place.getCoordinate().getY();
        this.place_name = place.getName();
    }

    @Builder
    public PlaceDto(Long id, String address_name, String place_name, String category_name, String x, String y) {
        this.id = id;
        this.address_name = address_name;
        this.category_name = category_name;
        this.place_name = place_name;
        this.x = x;
        this.y = y;
    }
}
