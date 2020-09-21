package com.gate.planner.gate.service.course;

import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final PlaceService placeService;
    private final UserRepository userRepository;

    public CourseResponseDto saveCourse(CourseRequestDto courseRequestDto) {
        User user = userRepository.findByUserName("ktj7916").orElseThrow(UserNotExistException::new);
        List<PlaceWrapperResponseDto> places = new ArrayList<>();
        int totalCost = 0;
        Course course = courseRepository.save(
                Course.builder()
                        .title(courseRequestDto.getCourseName())
                        .content(courseRequestDto.getContent())
                        .user(user).build());

        for (PlaceWrapperDto placeWrapperDto : courseRequestDto.getPlaces()) {
            PlaceWrapper placeWrapper = placeService.savePlaceWrapper(placeWrapperDto,course);
            totalCost += placeWrapper.getCost();
            places.add(new PlaceWrapperResponseDto(placeWrapper));
        }
        return CourseResponseDto.builder()
                .id(course.getId())
                .content(course.getContent())
                .title(course.getTitle())
                .totalCost(totalCost)
                .places(places)
                .userName(user.getUserName())
                .build();
    }
}
