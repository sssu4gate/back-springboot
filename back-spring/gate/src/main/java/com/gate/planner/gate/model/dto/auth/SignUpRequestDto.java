package com.gate.planner.gate.model.dto.auth;

import com.gate.planner.gate.model.entity.user.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@ApiModel
@Getter
public class SignUpRequestDto {

    @ApiModelProperty("유저 식별자")
    Long id;
    @ApiModelProperty("닉네임")
    String nickName;
    @ApiModelProperty("성별")
    Gender gender;
    @ApiModelProperty("유저 생일(yyyy-mm-dd)")
    String birth;
    @ApiModelProperty("카카오 AccesSToken")
    String accessToken;
    @ApiModelProperty("카카오 RefreshToken")
    String refreshToken;
}
