package com.gate.planner.gate.model.dto.user;

import com.gate.planner.gate.model.entity.user.Gender;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel
@Getter
public class UserInfoDto {
    @ApiModelProperty("유저 식별자")
    Long id;

    @ApiModelProperty("좋아요")
    int likeNum;

    @ApiModelProperty("닉네임")
    String nickName;

    @ApiModelProperty("유저 프로필 사진")
    String userImgUrl;

    @ApiModelProperty("생년월일")
    String birth;

    @ApiModelProperty("성별")
    Gender gender;

    public UserInfoDto(User user) {
        this.id = user.getId();
        this.userImgUrl = user.getImgUrl();
        this.likeNum = user.getLikeNum();
        this.nickName = user.getNickName();
        this.birth = DateUtil.parseString(user.getBirth());
        this.gender = user.getGender();
    }
}
