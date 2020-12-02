package com.gate.planner.gate;

import com.gate.planner.gate.controller.CourseController;
import com.gate.planner.gate.controller.SearchController;
import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.exception.course.AlreadyReportedException;
import com.gate.planner.gate.exception.course.CourseRequestTypeInvalidException;
import com.gate.planner.gate.factory.CommonFactory;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseRequestType;
import com.gate.planner.gate.model.entity.course.CourseSearchType;
import com.gate.planner.gate.model.entity.course.report.CourseReportType;
import com.gate.planner.gate.service.course.CourseService;
import com.gate.planner.gate.service.place.PlaceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class CourseTest extends CommonFactory {

    @Autowired
    SearchController searchController;

    @Autowired
    CourseController courseController;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PlaceService placeService;

    @BeforeEach
    public void setUser() {
        Assertions.assertDoesNotThrow(() -> userFactory.returnSignUpUser());
        Assertions.assertDoesNotThrow(() -> placeService.decideCoursePlaces(placeFactory.returnFirstPlaceDtoList()));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId(), ""));
    }


    /**
     * CourseRequestDto를 Factory에서 생성,
     * Service로직으로 저장 후, 해당 Course를 저장한 User의 닉네임으로 Course를 검색 했을 때 0개가 아니어야한다.
     */
    @Test
    public void saveCourseTest() {
        CourseRequestDto courseRequestDto = courseFactory.returnCourseRequestDto();
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> courseController.saveCourse(courseRequestDto)),
                () -> Assertions.assertDoesNotThrow(() -> {
                    Assertions.assertNotEquals(0,
                            searchController.getCourseSearchList(userFactory.getNickName(), CourseSearchType.WRITE, 1, 5).size()
                    );
                    Assertions.assertEquals(0, searchController.getCourseSearchList("0", CourseSearchType.MONEY, 1, 5).size());
                })
        );
    }

    @Test
    public void updateCourseTest() {
        CourseRequestDto courseUpdateDto = courseFactory.returnUpdateCourseRequestDto();
        Assertions.assertDoesNotThrow(() -> placeService.decideCoursePlaces(placeFactory.returnSecondPlaceDtoList()));
        Assertions.assertDoesNotThrow(
                () -> courseController.updateCourse(courseFactory.returnSaveCourse().getId(), courseUpdateDto));
        Assertions.assertEquals(courseUpdateDto.getCourseName(), courseFactory.findCourseAtDB().get(0).getTitle());
    }

    @Test
    public void likeCourseTest() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> courseFactory.returnSaveCourse()),
                () -> {
                    Course course = courseFactory.findCourseAtDB().get(0);
                    Assertions.assertDoesNotThrow(() -> courseController.likeCourse(course.getId()));
                    Assertions.assertEquals(1, course.getLikeNum());
                }
        );
    }

    @Test
    public void returnBasicCourseListTest() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> placeService.decideCoursePlaces(placeFactory.returnSecondPlaceDtoList())),
                () -> Assertions.assertDoesNotThrow(() -> courseController.saveCourse(courseFactory.returnUpdateCourseRequestDto())),
                () -> Assertions.assertDoesNotThrow(() -> courseController.saveCourse(courseFactory.returnCourseRequestDto())),
                () -> Assertions.assertThrows(CourseRequestTypeInvalidException.class, () -> courseService.basicCourseList(null, 1, 5)),
                () -> Assertions.assertNotEquals(2, courseController.responseBasicCourseList(CourseRequestType.LATEST, 1, 5).size()),
                () -> Assertions.assertNotEquals(2, courseController.responseBasicCourseList(CourseRequestType.LIKE, 1, 5).size())
        );
    }

    @Test
    public void reportCourseTest() {
        Assertions.assertAll(
                () -> {
                    Course course = courseFactory.returnSaveCourse();
                    Assertions.assertDoesNotThrow(() -> courseController.reportCourse(course.getId(), CourseReportType.OVERLAP));
                    Assertions.assertThrows(AlreadyReportedException.class, () -> courseController.reportCourse(course.getId(), CourseReportType.ADVERTISE), "이미 신고한 게시글입니다.");
                    Assertions.assertEquals(1, course.getReportNum());
                }
        );
    }

    @Test
    public void courseDetailTest() {
        Assertions.assertAll(
                () -> {
                    Course course = courseFactory.returnSaveCourse();
                    Assertions.assertDoesNotThrow(
                            () -> Assertions.assertEquals(courseFactory.findCourseAtDB().get(0).getId(), courseService.courseDetail(course.getId()).getId())
                    );
                }
        );
    }

    @Test
    public void searchCourseTest() {
        Assertions.assertAll(
                () -> {
                    Course course = courseFactory.returnSaveCourse();
                    Assertions.assertDoesNotThrow(() ->
                            Assertions.assertEquals(1, courseService.searchCourse(userFactory.getNickName(), CourseSearchType.WRITE, 1, 5).size())
                    );
                    Assertions.assertDoesNotThrow(() -> {
                        Assertions.assertNotEquals(1, courseService.searchCourse("0", CourseSearchType.MONEY, 1, 5));
                    });
                }
        );
    }
}
