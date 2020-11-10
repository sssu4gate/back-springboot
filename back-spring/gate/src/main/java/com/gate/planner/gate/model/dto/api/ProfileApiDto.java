package com.gate.planner.gate.model.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileApiDto {

    @ApiModelProperty("유저 식별자")
    private Long id;
    @ApiModelProperty("유저 정보")
    private KakaoUserProperties properties;
}
