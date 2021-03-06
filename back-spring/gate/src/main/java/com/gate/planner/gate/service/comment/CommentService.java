package com.gate.planner.gate.service.comment;

import com.gate.planner.gate.dao.coment.CommentRepository;
import com.gate.planner.gate.dao.course.CourseRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.course.CommentAccessDenyException;
import com.gate.planner.gate.exception.course.CommentNotExistsException;
import com.gate.planner.gate.exception.course.CourseNotExistException;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.comment.response.CommentResponseDto;
import com.gate.planner.gate.model.entity.comment.Comment;
import com.gate.planner.gate.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CourseRepository courseRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    /**
     * post별 댓글 조회
     */
    public List<CommentResponseDto> getComment(Long courseId) {
        return commentRepository.findByCourse_Id(courseId).stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 댓글 입력
     */
    @Transactional
    public Long saveComment(Long courseId, String content) {
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).
                orElseThrow(UserNotExistException::new);
        Comment comment = commentRepository.save(
                Comment.builder().
                        content(content).
                        course(courseRepository.findById(courseId).orElseThrow(CourseNotExistException::new)).
                        user(user).
                        build()
        );

        return comment.getId();
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        if (comment.getUser().equals(user))
            commentRepository.deleteById(id);
        else
            throw new CommentAccessDenyException();

    }

    /**
     * 댓글 수정
     */
    public void modifyComment(Long id, String content) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
        User user = userRepository.findById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())).orElseThrow(UserNotExistException::new);
        if (comment.getUser().equals(user))
            comment.setContent(content);
        else
            throw new CommentAccessDenyException();
    }
}
