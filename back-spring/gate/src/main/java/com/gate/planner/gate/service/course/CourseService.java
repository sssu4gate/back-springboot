package com.gate.planner.gate.service.course;

import com.gate.planner.gate.dao.course.CourseLikeRepository;
import com.gate.planner.gate.dao.course.CourseMemoRepository;
import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.course.CourseNotExistException;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseLike;
import com.gate.planner.gate.model.entity.course.CourseMemo;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.place.PlaceService;
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
    private final UserRepository userRepository;
    private final CourseMemoRepository courseMemoRepository;
    private final CourseLikeRepository courseLikeRepository;


    /*
        코스 저장
     */
    @Transactional
    public CourseResponseDto saveCourse(CourseRequestDto courseRequestDto) {
        // 추후에 User 회원가입 로직이 생기면 바꿀 것 --> 지금은 import.sql에 기본적으로 ktj7916이 저장하게 했음.
        User user = userRepository.findByUserName("ktj7916").orElseThrow(UserNotExistException::new);
        List<PlaceWrapperResponseDto> places = new ArrayList<>();
        List<String> memos = null;
        int totalCost = 0;
        Course course = courseRepository.save(
                Course.builder()
                        .title(courseRequestDto.getCourseName())
                        .content(courseRequestDto.getContent())
                        .user(user).build());

        /*
            placeWrapper를 생성해주고 Course와 연관관계 맺어주기
         */
        for (PlaceWrapperDto placeWrapperDto : courseRequestDto.getPlaces()) {
            PlaceWrapper placeWrapper = placeService.savePlaceWrapper(placeWrapperDto, course);
            totalCost += placeWrapper.getCost();
            places.add(new PlaceWrapperResponseDto(placeWrapper));
        }
        /*
            CourseMemo를 만들어주고 연관관계 맺어주기
         */
        if (courseRequestDto.getMemos() != null) {
            memos = new ArrayList<>();
            for (String memo : courseRequestDto.getMemos())
                memos.add(courseMemoRepository.save(CourseMemo.builder()
                        .course(course)
                        .content(memo).build()).getContent());
        }


        return CourseResponseDto.builder()
                .id(course.getId())
                .content(course.getContent())
                .title(course.getTitle())
                .totalCost(totalCost)
                .places(places)
                .memos(memos)
                .userName(user.getUserName())
                .build();
    }

    /*
        우선 Like 되어있는지 확인하기 위해서 검색을 하고, 이미 존재 할 경우 삭제 + course 에서 likeNum 감소시키고,
        없다면 Like 정보를 DB에 담고, course 에서 likeNum을 하나 증가시킨다.
     */
    @Transactional
    public void likeCourse(Long id, String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(UserNotExistException::new);
        Course course = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);

        if (courseLikeRepository.existsByCourseAndUser(course, user)) {
            course.setLikeNum(course.getLikeNum() - 1);
            courseLikeRepository.deleteByCourseAndUser(course, user);
        } else {
            courseLikeRepository.save(CourseLike.builder()
                    .course(course)
                    .user(user).build());

            course.setLikeNum(course.getLikeNum() + 1);
        }
    }
}
