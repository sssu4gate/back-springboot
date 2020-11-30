package com.gate.planner.gate;

import com.gate.planner.gate.exception.auth.NickNameAlreadyExistException;
import com.gate.planner.gate.exception.course.CourseSearchTypeWrongException;
import com.gate.planner.gate.factory.CommonFactory;
import com.gate.planner.gate.model.entity.course.CourseSearchType;
import com.gate.planner.gate.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@SpringBootTest
@Transactional
public class UserTest extends CommonFactory {
    @Autowired
    UserService userService;

    @BeforeEach
    public void setJwtToken() throws ParseException {
        Assertions.assertDoesNotThrow(() -> userFactory.returnSignUpUser());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId(), ""));
    }

    @Test
    public void findUserRelatedPostTest() {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertNotNull(userService.findUserRelatedPost(0, CourseSearchType.LIKE, 5))),
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertNotNull(userService.findUserRelatedPost(0, CourseSearchType.WRITE, 5))),
                () -> Assertions.assertThrows(CourseSearchTypeWrongException.class,
                        () -> userService.findUserRelatedPost(0, CourseSearchType.PLACE, 5),
                        "올바르지 않은 요청 타입입니다.")
        );
    }

    @Test
    public void updateNickTest() throws ParseException {
        Assertions.assertAll(
                () -> Assertions.assertThrows(NickNameAlreadyExistException.class,
                        () -> userService.updateNickName(userFactory.getNickName()),
                        "이미 존재하는 닉네임 입니다."),
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertEquals(userFactory.getNewNick(), userService.updateNickName(userFactory.getNewNick()))),
                () -> Assertions.assertDoesNotThrow(() ->
                        Assertions.assertNotEquals(userFactory.findUser(userFactory.getId()).getNickName(), userFactory.getNickName()))
        );
    }
}
