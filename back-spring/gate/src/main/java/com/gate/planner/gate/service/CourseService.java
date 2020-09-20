package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.model.dto.request.place.PlaceDto;
import com.gate.planner.gate.model.entity.place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final PlaceService placeService;
}
