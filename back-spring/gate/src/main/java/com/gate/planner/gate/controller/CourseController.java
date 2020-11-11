package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDetailDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.entity.course.CourseRequestType;
import com.gate.planner.gate.service.course.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @ApiOperation("코스 저장")
    @PostMapping("/save")
    public CourseResponseDetailDto saveCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return courseService.saveCourse(courseRequestDto);
    }

    @ApiOperation("코스 좋아요 기능")
    @GetMapping("/like/{id}")
    public void likeCourse(@PathVariable Long id) {
        courseService.likeCourse(id);
    }
}
