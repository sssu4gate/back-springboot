package com.gate.planner.gate.model.dto.place;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gate.planner.gate.model.entity.place.Place;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDto {

    @ApiModelProperty("장소 식별자")
    private Long id;
    @ApiModelProperty("주소")
    private String address_name;
    @ApiModelProperty("장소 이름")
    private String place_name;
    @ApiModelProperty("카테고리")
    private String category_name;
    @ApiModelProperty("경도")
    private String x;
    @ApiModelProperty("위도")
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
