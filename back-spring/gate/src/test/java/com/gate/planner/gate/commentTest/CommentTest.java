package com.gate.planner.gate.commentTest;

import com.gate.planner.gate.dao.coment.CommentRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.model.dto.comment.request.CommentRequestDto;
import com.gate.planner.gate.model.entity.comment.Comment;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.comment.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CommentTest {

    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void postComment(){
        // given
        User user = User.builder().userName("ktj7916")
                .Email("rlaxowns7916@gmail.com")
                .nickName("행알행알")
                .password("asdasdad11111!!!")
                .build();
        userRepository.save(user);   // 임시
        Course course = Course.builder().content("testContent").title("TestTitle").user(user).build();
        Comment comment = Comment.builder().content("testContent")
                .course(course)
                .user(user).build();
        CommentRequestDto commentRequestDto = new CommentRequestDto(comment.getContent(), comment.getCourse());
        // when
        Long saveId = commentService.saveComment(commentRequestDto);
        // then
        Comment findComment = commentRepository.findById(saveId).get();
        assertThat(comment.getContent()).isEqualTo(findComment.getContent());
    }

    @Test
    void deleteComment(){
        // given
        User user = User.builder().userName("ktj7916")
                .Email("rlaxowns7916@gmail.com")
                .nickName("행알행알")
                .password("asdasdad11111!!!")
                .build();
        userRepository.save(user);   // 임시
        Course course = Course.builder().content("testContent").title("TestTitle").user(user).build();
        Comment comment = Comment.builder().content("testContent")
                .course(course)
                .user(user).build();
        CommentRequestDto commentRequestDto = new CommentRequestDto(comment.getContent(), comment.getCourse());
        // when
        Long saveId = commentService.saveComment(commentRequestDto);
        Long deleteId = commentService.deleteComment(saveId);
        // then
        assertThat(saveId == deleteId);
    }

    @Test
    void modifyComment(){
        // given
        User user = User.builder().userName("ktj7916")
                .Email("rlaxowns7916@gmail.com")
                .nickName("행알행알")
                .password("asdasdad11111!!!")
                .build();
        userRepository.save(user);   // 임시
        Course course = Course.builder().content("testContent").title("TestTitle").user(user).build();
        Comment comment = Comment.builder().content("testContent")
                .course(course)
                .user(user).build();
        CommentRequestDto commentRequestDto = new CommentRequestDto(comment.getContent(), comment.getCourse());
        // when
        Long saveId = commentService.saveComment(commentRequestDto);
        Long modifyId = commentService.modifyComment(saveId, "modify!");
        // then
        assertThat(saveId == modifyId);
    }
}
