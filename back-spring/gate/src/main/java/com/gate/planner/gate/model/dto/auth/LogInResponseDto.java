package com.gate.planner.gate.model.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
public class LogInResponseDto {
    @ApiModelProperty("어플리케이션 AccessToken")
    String accessToken;
    @ApiModelProperty("어플리케이션 RefreshToken")
    String refreshToken;

    public LogInResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
