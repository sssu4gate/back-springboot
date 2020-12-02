package com.gate.planner.gate.model.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@AllArgsConstructor
public class LoginRequestDto {
    @ApiModelProperty("카카오 AccessToken")
    String accessToken;
    @ApiModelProperty("카카오 RefreshToken")
    String refreshToken;
}
