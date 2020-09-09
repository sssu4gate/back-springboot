package com.gate.planner.gate.model.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class SignUpRequestDto {

    @Size(min = 6, max = 10, message = "길이가 알맞지 않습니다.")
    String userName;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    String password;

    String nickname;

    @Email
    String Email;
}
