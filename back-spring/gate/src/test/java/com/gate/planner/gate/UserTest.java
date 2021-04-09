package com.gate.planner.gate;

import com.gate.planner.gate.controller.AuthController;
import com.gate.planner.gate.controller.UserController;
import com.gate.planner.gate.exception.course.CourseSearchTypeWrongException;
import com.gate.planner.gate.factory.CommonFactory;
import com.gate.planner.gate.model.entity.course.UserRelatedCourseSearchType;
import com.gate.planner.gate.service.auth.AuthService;
import com.gate.planner.gate.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@SpringBootTest
@Transactional
public class UserTest extends CommonFactory {

    @RegisterExtension
    CustomExtension extension = new CustomExtension();

    @Autowired
    AuthController authController;

    @Autowired
    UserController userController;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @BeforeEach
    public void setJwtToken() throws ParseException {
        Assertions.assertDoesNotThrow(() -> userFactory.returnSignUpUser1());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId1(), ""));
    }

    @Test
    public void findUserRelatedPostTest() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertNotNull(userController.userRelatedCourse(UserRelatedCourseSearchType.LIKE, 1, 5, null, null))),
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertNotNull(userController.userRelatedCourse(UserRelatedCourseSearchType.WRITE, 1, 5, null, null))),
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertNotNull(userController.userRelatedCourse(UserRelatedCourseSearchType.DATE, 1, 5, "2020-12-01", "2020-12-31"))),
                () -> Assertions.assertThrows(NullPointerException.class,
                        () -> userController.userRelatedCourse(null, 1, 5, null, null),
                        "올바르지 않은 요청 타입입니다.")
        );
    }

    @Test
    public void updateNickTest() throws ParseException {
        Assertions.assertAll(
                () -> Assertions.assertTrue(() -> authController.checkNickNameExist(userFactory.getNewNick())),
                () -> Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(userFactory.getNewNick(), userController.updateNickName(userFactory.getNewNick()))),
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertNotEquals(userFactory.findUser(userFactory.getId1()).getNickName(), userFactory.getNickName1()))
        );
    }
}
