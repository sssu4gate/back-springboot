package com.gate.planner.gate.model.dto.place;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ApiModel
@AllArgsConstructor
public class PlaceWrapperRequestDto {
    @ApiModelProperty("Place의 식별자")
    Long id;
    @ApiModelProperty("그 장소에서의 사용 금액")
    int cost;
    @ApiModelProperty("그 장소에서 보낸 시간")
    String time;
}
