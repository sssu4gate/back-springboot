package com.gate.planner.gate.controller;

import com.gate.planner.gate.dao.course.projection.CourseOnly;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDetailDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.entity.course.CourseRequestType;
import com.gate.planner.gate.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    /*
    private final CourseService courseService;

    @PostMapping("/save")
    public CourseResponseDetailDto saveCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return courseService.saveCourse(courseRequestDto);
    }

    @GetMapping("/like/{id}")
    public void likeCourse(@PathVariable Long id, @RequestParam String userName) {
        courseService.likeCourse(id, userName);
    }

    @GetMapping
    public List<CourseResponseDto> getCourses(@RequestParam String userName, @RequestParam CourseRequestType type, @RequestParam int page) {
        return courseService.findCourse(userName, type, page);
    }

     */
}
