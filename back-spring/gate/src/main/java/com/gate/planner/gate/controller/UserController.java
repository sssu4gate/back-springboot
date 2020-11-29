package com.gate.planner.gate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.user.UserInfoDto;
import com.gate.planner.gate.model.entity.course.CourseSearchType;
import com.gate.planner.gate.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation("내 정보 보기")
    @GetMapping("/info/profile")
    public UserInfoDto userInfoProfile() throws JsonProcessingException {
        return userService.findProfile();
    }
    @ApiOperation(("나와 관련된 코스 보기"))
    @GetMapping("/info/course")
    public List<CourseResponseDto> userRelatedCourse(@RequestParam CourseSearchType type, @RequestParam int page)
    {
        return userService.findUserRelatedPost(page,type);
    }


}
