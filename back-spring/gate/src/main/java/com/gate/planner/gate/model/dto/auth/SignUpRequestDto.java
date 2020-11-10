package com.gate.planner.gate.model.dto.auth;

import com.gate.planner.gate.model.entity.user.Gender;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignUpRequestDto {

    Long id;
    String nickName;
    Gender gender;
    String birth;
    String accessToken;
    String refreshToken;
}
