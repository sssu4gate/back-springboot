package com.gate.planner.gate.model.dto.user;

import com.gate.planner.gate.model.dto.api.ProfileApiDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.entity.user.Gender;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@ApiModel
@Getter
public class UserInfoDto {
    @ApiModelProperty("유저 정보")
    ProfileApiDto userInfo;

    @ApiModelProperty("좋아요")
    int likeNum;

    @ApiModelProperty("닉네임")
    String nickName;

    @ApiModelProperty("생년월일")
    String birth;

    @ApiModelProperty("성별")
    Gender gender;

    public UserInfoDto(ProfileApiDto profileApiDto, User user) {
        this.userInfo = profileApiDto;
        this.likeNum = user.getLikeNum();
        this.nickName = user.getNickName();
        this.birth = DateUtil.parseString(user.getBirth());
        this.gender = user.getGender();
    }
}
