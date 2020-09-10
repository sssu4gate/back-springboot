package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.request.place.WriteCourseRequestDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final PlaceService placeService;
    private final PostService postService;

    @Transactional
    public void saveCourse(WriteCourseRequestDto writeCourseRequestDto, MultipartFile[] images) throws IOException {
        User user = userRepository.findByUserName(writeCourseRequestDto.getUserName()).orElseThrow(UserNotExistException::new);
        Course course = Course.builder().name(writeCourseRequestDto.getCourseName()).user(user).build();
        List<Place> places = placeService.savePlaceAndCourse(writeCourseRequestDto.getPlaces());
        postService.savePost(course, places, writeCourseRequestDto, images);
        courseRepository.save(course);
    }


}
