package com.gate.planner.gate.model.dto.user.response;

import com.gate.planner.gate.model.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String nickName;

    public UserResponseDto(User user){
        this.nickName = user.getNickName();
    }
}
