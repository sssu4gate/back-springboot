package com.gate.planner.gate.model.dto.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserProperties {
    @ApiModelProperty("카카오 닉네임")
    String nickname;
    @ApiModelProperty("카카오 프로필 사진")
    String profile_image;
    @ApiModelProperty("카카오 프로필 썸네일 사진")
    String thumbnail_image;
}
