package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Transactional
    public Course saveCourse(String courseName, String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(UserNotExistException::new);
        Course course = Course.builder().name(courseName).user(user).build();

        return courseRepository.save(course);
    }
}
