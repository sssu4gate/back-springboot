package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.model.entity.course.CourseSearchType;
import com.gate.planner.gate.service.api.ApiService;
import com.gate.planner.gate.service.course.CourseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final ApiService apiService;
    private final CourseService courseService;

    @ApiOperation("키워드로 장소들 검색")
    @GetMapping("/place")
    public ArrayList<PlaceDto> getPlaceSearchResult(@RequestParam String keyword, @RequestParam int page, @RequestParam int offset) throws IOException {
        return apiService.callLocationAPI(page, keyword, offset);
    }

    @ApiOperation("키워드로 코스 검색")
    @GetMapping("/course")
    public List<CourseResponseDto> getCourseSearchList(@RequestParam String keyword, @RequestParam CourseSearchType type, @RequestParam int page, @RequestParam int offset) {
        return courseService.searchCourse(keyword, type, page, offset);
    }

}
