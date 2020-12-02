package com.gate.planner.gate;

import com.gate.planner.gate.controller.CourseController;
import com.gate.planner.gate.exception.course.CommentAccessDenyException;
import com.gate.planner.gate.exception.course.CommentNotExistsException;
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
        Assertions.assertDoesNotThrow(() -> userFactory.returnSignUpUser1());
        Assertions.assertDoesNotThrow(() -> userFactory.returnSignUpUser2());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId1(), ""));

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
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId2(), ""));
                    Assertions.assertThrows(CommentAccessDenyException.class, () -> courseController.deleteComment(course.getId(), commentId), "삭제 권한이 없습니다.");
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId1(), ""));
                    Assertions.assertDoesNotThrow(() -> courseController.deleteComment(course.getId(), commentId));
                    Assertions.assertThrows(CommentNotExistsException.class, () -> courseController.deleteComment(course.getId(), commentId), "존재하지 않는 댓글입니다.");
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
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId2(), ""));
                    Assertions.assertThrows(CommentAccessDenyException.class, () -> courseController.updateComment(course.getId(), commentId, commentFactory.getUpdateContent()), "수정 권한이 없습니다.");
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userFactory.getId1(), ""));
                    Assertions.assertDoesNotThrow(() -> courseController.updateComment(course.getId(), commentId, commentFactory.getUpdateContent()));
                    Assertions.assertEquals(commentFactory.getUpdateContent(), commentFactory.findCommentAtDB().get(0).getContent());
                }
        );
    }

    @Test
    public void getCommentTest() {
        Assertions.assertAll(
                () -> {
                    Course course = courseFactory.returnSaveCourse();
                    Assertions.assertDoesNotThrow(
                            () -> Assertions.assertEquals(0, courseController.getComment(course.getId()).size())
                    );
                    Assertions.assertDoesNotThrow(() -> courseController.writeComment(course.getId(), commentFactory.getContent()));
                    Assertions.assertDoesNotThrow(
                            () -> Assertions.assertNotEquals(0, courseController.getComment(course.getId()).size())
                    );

                }
        );
    }

}
