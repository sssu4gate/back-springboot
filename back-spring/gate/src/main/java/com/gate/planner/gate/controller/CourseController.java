package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.request.place.WriteCourseRequestDto;
import com.gate.planner.gate.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public void saveCourse(@RequestPart WriteCourseRequestDto writeCourseRequestDto, @RequestPart MultipartFile[] images) throws IOException {
        courseService.saveCourse(writeCourseRequestDto, images);
    }
}
