package com.gate.planner.gate;

import com.gate.planner.gate.dao.common.CommonPage;
import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.factory.CommonFactory;
import com.gate.planner.gate.model.dto.course.request.CourseRequestDto;
import com.gate.planner.gate.model.entity.course.ShareType;
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

@SpringBootTest
@Transactional
public class CourseTest extends CommonFactory {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PlaceService placeService;

    @BeforeEach
    public void setUser() {
        Assertions.assertDoesNotThrow(() -> userFactory.returnSignUpUser());
        Assertions.assertDoesNotThrow(() -> placeService.decideCoursePlaces(placeFactory.returnPlaceDtoList()));
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
                () -> Assertions.assertDoesNotThrow(() -> courseService.saveCourse(courseRequestDto)),
                () -> Assertions.assertDoesNotThrow(() -> {
                    Assertions.assertNotEquals(0,
                            courseRepository.findAllByUser_NickNameAndShareType(userFactory.getNickName(), new CommonPage(0, 5), ShareType.PUBLIC).size()
                    );
                })
        );

    }
}
