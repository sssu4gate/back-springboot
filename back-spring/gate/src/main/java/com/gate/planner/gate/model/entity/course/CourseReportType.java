package com.gate.planner.gate.model.entity.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public enum CourseReportType {
    @ApiModelProperty("폭력적인 게시물")
    VIOLENT,
    @ApiModelProperty("광고성 게시물")
    ADVERTISE,
    @ApiModelProperty("도배성 게시물")
    OVERLAP,
    @ApiModelProperty("음란성 게시물")
    OBSCENE,
    @ApiModelProperty("혐오성 게시글")
    AVERSION,
    @ApiModelProperty("정치성향 게시글")
    POLITICS

}
