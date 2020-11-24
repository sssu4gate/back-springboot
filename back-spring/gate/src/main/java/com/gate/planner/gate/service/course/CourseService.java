package com.gate.planner.gate.service.course;

import com.gate.planner.gate.dao.common.CommonPage;
import com.gate.planner.gate.dao.course.CourseLikeRepository;
import com.gate.planner.gate.dao.course.CourseMemoRepository;
import com.gate.planner.gate.dao.course.CourseReportRepository;
import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.dao.course.projection.CourseOnly;
import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.course.AlreadyReportedException;
import com.gate.planner.gate.exception.course.CourseNotExistException;
import com.gate.planner.gate.exception.course.CourseRequestTypeWrongException;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDetailDto;
import com.gate.planner.gate.model.dto.course.response.CourseResponseDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperResponseDto;
import com.gate.planner.gate.model.entity.course.*;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;
    private final PlaceService placeService;
    private final UserRepository userRepository;
    private final CourseMemoRepository courseMemoRepository;
    private final CourseLikeRepository courseLikeRepository;
    /**
     * 행알이가 추가한 코드>3<
     */
    private final CourseReportRepository courseReportRepository;


    /**
     * 코스 저장
     */
    @Transactional
    public CourseResponseDetailDto saveCourse(CourseRequestDto courseRequestDto) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        List<PlaceWrapperResponseDto> places = new ArrayList<>();
        List<String> memos = null;
        int totalCost = 0;
        Course course = courseRepository.save(
                Course.builder()
                        .title(courseRequestDto.getCourseName())
                        .content(courseRequestDto.getContent())
                        .shareType(courseRequestDto.getShareType())
                        .user(user).build());


        for (PlaceWrapperDto placeWrapperDto : courseRequestDto.getPlaces()) {
            PlaceWrapper placeWrapper = placeService.savePlaceWrapper(placeWrapperDto, course);
            totalCost += placeWrapper.getCost();
            places.add(new PlaceWrapperResponseDto(placeWrapper));
        }

        if (courseRequestDto.getMemos() != null) {
            memos = new ArrayList<>();
            for (String memo : courseRequestDto.getMemos())
                memos.add(courseMemoRepository.save(CourseMemo.builder()
                        .course(course)
                        .content(memo).build()).getContent());
        }


        return CourseResponseDetailDto.builder()
                .id(course.getId())
                .shareType(course.getShareType())
                .createdAt(course.getCreatedAt())
                .content(course.getContent())
                .title(course.getTitle())
                .totalCost(totalCost)
                .places(places)
                .memos(memos)
                .build();
    }

    /**
     * 코스 좋아요
     */
    @Transactional
    public int likeCourse(Long id) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        Course course = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);

        if (courseLikeRepository.existsByCourseAndUser(course, user)) {
            course.setLikeNum(course.getLikeNum() - 1);
            courseLikeRepository.deleteByCourseAndUser(course, user);
            course.getUser().setLikeNum(course.getUser().getLikeNum() - 1);
        } else {
            courseLikeRepository.save(CourseLike.builder()
                    .course(course)
                    .user(user).build());

            course.setLikeNum(course.getLikeNum() + 1);
            course.getUser().setLikeNum(course.getUser().getLikeNum() + 1);
        }

        /**
         * 행알이의 추가 코드
         */
        return course.getLikeNum();
    }

    /**
     * 코스 신고
     * 행알이의 추가코드
     */
    public void reportCourse(Long id, CourseReportType type) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        Course course = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);

        if (courseReportRepository.existsByCourseAndUser(course, user)) {
            AlreadyReportedException alreadyReportedException = new AlreadyReportedException();
            throw alreadyReportedException;
        } else {
            courseReportRepository.save(CourseReport.builder()
                    .course(course)
                    .type(type)
                    .user(user).build());

            course.setReportNum(course.getReportNum() + 1);
        }

        checkReportNum(course, course.getReportNum());
    }

    /**
     * 코스 신고 횟수 체크
     * 행알이가 추가한 코드
     */
    private void checkReportNum(Course course, int reportNum) {
        if (reportNum == 5)
            course.setReportFlag(true);
    }

    /**
     * 나와 연관된 코스정보
     */
    @Transactional
    public List<CourseResponseDto> findUserRelatedCourse(Long id, CourseRequestType type, int page) {
        User user = userRepository.findById(id).orElseThrow(UserNotExistException::new);
        List<CourseResponseDto> returnCourseList = new ArrayList<>();
        if (type.equals(CourseRequestType.LIKE)) {
            List<CourseOnly> courseList = courseLikeRepository.findAllByUser(user, new CommonPage(page));
            for (CourseOnly course : courseList)
                returnCourseList.add(new CourseResponseDto(course.getCourse()));

        } else if (type.equals(CourseRequestType.WRITE)) {
            List<Course> courseList = courseRepository.findAllByUser(user, new CommonPage(page));
            for (Course course : courseList)
                returnCourseList.add(new CourseResponseDto(course));
        } else
            throw new CourseRequestTypeWrongException();
        return returnCourseList;
    }

    /**
     * 코스 검색
     */
    @Transactional
    public List<CourseResponseDto> searchCourse(String keyWord, CourseRequestType type, int page) {
        if (type.equals(CourseRequestType.WRITE)) {
            return courseRepository.findAllByUser_NickNameAndShareType(keyWord, new CommonPage(page), CourseShareType.PUBLIC).stream().map(CourseResponseDto::new).collect(Collectors.toList());
        } else if (type.equals(CourseRequestType.MONEY)) {
            return courseRepository.findAllByTotalCostIsLessThanEqualAndShareType(Integer.parseInt(keyWord), new CommonPage(page), CourseShareType.PUBLIC).stream().map(CourseResponseDto::new).collect(Collectors.toList());
        } else if (type.equals(CourseRequestType.TAG)) {
            return null;
        } else {
            return courseRepository.findDistinctByTitleContainingOrContentContainingAndShareType(keyWord, keyWord, new CommonPage(page), CourseShareType.PUBLIC).stream().map(CourseResponseDto::new).collect(Collectors.toList());
        }
    }

    /**
     * 코스 상세보기
     */
    @Transactional
    public CourseResponseDetailDto courseDetail(long id) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        Course course = courseRepository.findById(id).orElseThrow(CourseNotExistException::new);
        return new CourseResponseDetailDto(course, user);
    }
}

