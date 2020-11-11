package com.gate.planner.gate.model.dto.user;

import com.gate.planner.gate.model.dto.api.ProfileApiDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel
@Getter
@NoArgsConstructor
public class UserInfoDto {
    @ApiModelProperty("유저 정보")
    ProfileApiDto userInfo;

    @ApiModelProperty("좋아요")
    int likeNum;

    public UserInfoDto(ProfileApiDto profileApiDto, int likeNum) {
        this.userInfo = profileApiDto;
        this.likeNum = likeNum;
    }

}
