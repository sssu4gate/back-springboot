package com.gate.planner.gate;

import com.gate.planner.gate.controller.CourseController;
import com.gate.planner.gate.factory.CommonFactory;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.service.comment.CommentService;
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
public class CommentTest extends CommonFactory {

    @Autowired
    CourseController courseController;

    @Autowired
    CommentService commentService;

    @BeforeEach
    public void setUpUser() {
        Assertions.assertDoesNotThrow(() -> userFactory.returnSignUpUser());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId(), ""));

    }

    @Test
    public void writeCommentTest() {
        Assertions.assertAll(
                () -> {
                    Course course = courseFactory.returnSaveCourse();
                    Assertions.assertDoesNotThrow(() -> courseController.writeComment(course.getId(), commentFactory.getContent()));
                }
        );
    }

    @Test
    public void deleteCommentTest() {
        Assertions.assertAll(
                () -> {
                    Course course = courseFactory.returnSaveCourse();
                    Long commentId = commentService.saveComment(course.getId(), commentFactory.getContent());
                    Assertions.assertEquals(1, commentFactory.findCommentAtDB().size());
                    Assertions.assertDoesNotThrow(() -> courseController.deleteComment(course.getId(), commentId));
                    Assertions.assertNotEquals(1, commentFactory.findCommentAtDB().size());
                }
        );
    }

    @Test
    public void updateCommentTest() {
        Assertions.assertAll(
                () -> {
                    Course course = courseFactory.returnSaveCourse();
                    Long commentId = commentService.saveComment(course.getId(), commentFactory.getContent());
                    Assertions.assertDoesNotThrow(() -> courseController.updateComment(course.getId(), commentId, commentFactory.getUpdateContent()));
                    Assertions.assertEquals(commentFactory.getUpdateContent(), commentFactory.findCommentAtDB().get(0).getContent());
                }
        );
    }

}
