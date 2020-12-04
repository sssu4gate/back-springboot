package com.gate.planner.gate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.user.UserInfoDto;
import com.gate.planner.gate.model.entity.course.UserRelatedCourseSearchType;
import com.gate.planner.gate.service.course.CourseService;
import com.gate.planner.gate.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CourseService courseService;

    @ApiOperation("내 정보 보기")
    @GetMapping("/info/profile")
    public UserInfoDto userInfoProfile() throws JsonProcessingException {
        return userService.findProfile();
    }

    @ApiOperation(("나와 관련된 코스 보기"))
    @GetMapping("/info/course")
    public List<CourseResponseDto> userRelatedCourse(@RequestParam UserRelatedCourseSearchType type, @RequestParam int page, @RequestParam int offset, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws ParseException {
        return courseService.findUserRelatedCourse(type, page, offset, startDate, endDate);
    }

    @ApiOperation("닉네임 수정")
    @PutMapping("/info/nickname")
    public String updateNickName(@RequestParam String newNick) {
        return userService.updateNickName(newNick);
    }

    @ApiOperation("프로필 사진 수정")
    @PutMapping(value = "/info/profileImg", consumes = (MediaType.ALL_VALUE))
    public UserInfoDto updateProfile(@RequestPart MultipartFile image) throws IOException {
        return userService.updateUserProfileImg(image);
    }
}
