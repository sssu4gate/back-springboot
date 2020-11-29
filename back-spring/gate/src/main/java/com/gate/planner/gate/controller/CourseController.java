package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDetailDto;
import com.gate.planner.gate.model.entity.course.report.CourseReportType;
import com.gate.planner.gate.service.course.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
    public int likeCourse(@PathVariable Long id) {
        /**
         * 행알이의 추가 코드
         */
        return courseService.likeCourse(id);
    }

    /**
     * 행알이가 추가한 코드
     */
    @ApiOperation("코스 신고 기능")
    @GetMapping("/report/{id}")
    public void reportCourse(@PathVariable Long id, @RequestParam CourseReportType type) {
        courseService.reportCourse(id, type);
    }

    @ApiOperation("코스 상세보기")
    @GetMapping("/{id}")
    public CourseResponseDetailDto responseCourseDetail(@PathVariable Long id) {
        return courseService.courseDetail(id);
    }
}
