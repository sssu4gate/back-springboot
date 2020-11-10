package com.gate.planner.gate.service.comment;

import com.gate.planner.gate.dao.coment.CommentRepository;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.comment.request.CommentRequestDto;
import com.gate.planner.gate.model.dto.comment.response.CommentResponseDto;
import com.gate.planner.gate.model.entity.comment.Comment;
import com.gate.planner.gate.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    /**
     * post별 댓글 조회
     */
    public List<CommentResponseDto> getComment() {
        List<CommentResponseDto> commentList = new ArrayList<>();
        commentRepository.findAll().stream().map(it -> commentList.add(new CommentResponseDto(it)));
        return commentList;
    }

    /**
     * 댓글 입력
     */
    /*
    public Long saveComment(CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName())).
        orElseThrow(UserNotExistException::new);   // 임시
        Comment comment = commentRepository.save(
                Comment.builder().
                        content(commentRequestDto.getContent()).
                        course(commentRequestDto.getCourse()).
                        user(user).
                        build()
        );

        return comment.getId();
    }

     */

    /**
     * 댓글 삭제
     */
    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);

        return id;
    }

    /**
     * 댓글 수정
     */
    public Long modifyComment(Long id, String content) {
        commentRepository.findById(id).get().setContent(content);

        return id;
    }
}
