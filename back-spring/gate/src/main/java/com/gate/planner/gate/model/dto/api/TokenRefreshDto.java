package com.gate.planner.gate.model.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenRefreshDto {
    @ApiModelProperty("카카오 AccessToken")
    String access_token;
    @ApiModelProperty("카카오 RefreshToken")
    String refresh_token;
}
