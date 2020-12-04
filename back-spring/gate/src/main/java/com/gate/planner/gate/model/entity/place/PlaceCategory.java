package com.gate.planner.gate.model.entity.place;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel
@Getter
public enum PlaceCategory {
    @ApiModelProperty("대형마트")
    MT1,
    @ApiModelProperty("편의점")
    CS2,
    @ApiModelProperty("어린이집,유치원")
    PS3,
    @ApiModelProperty("학교")
    SC4,
    @ApiModelProperty("학원")
    AC5,
    @ApiModelProperty("주차장")
    PK6,
    @ApiModelProperty("주유소,충전소")
    OL7,
    @ApiModelProperty("지하철 역")
    SW8,
    @ApiModelProperty("은행")
    BK9,
    @ApiModelProperty("문화 시설")
    CT1,
    @ApiModelProperty("중개 업소")
    AG2,
    @ApiModelProperty("공공 기관")
    PO3,
    @ApiModelProperty("관광 명소")
    AT4,
    @ApiModelProperty("숙박")
    AD5,
    @ApiModelProperty("음식점")
    FD6,
    @ApiModelProperty("카페")
    CE7,
    @ApiModelProperty("병원")
    HP8,
    @ApiModelProperty("약국")
    PM9
}
