package com.gate.planner.gate.model.dto.place;

import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
public class PlaceWrapperResponseDto {

    @ApiModelProperty("장소Wrapper의 식별자")
    Long id;
    @ApiModelProperty("그 장소에서 사용한 금액")
    int cost;
    @ApiModelProperty("그 장소에서 지낸 시간")
    String time;
    @ApiModelProperty("장소 정보")
    PlaceDto placeDto;

    public PlaceWrapperResponseDto(PlaceWrapper placeWrapper) {
        this.id = placeWrapper.getId();
        this.cost = placeWrapper.getCost();
        this.placeDto = new PlaceDto(placeWrapper.getPlace());
        this.time = placeWrapper.getTime();
    }

}