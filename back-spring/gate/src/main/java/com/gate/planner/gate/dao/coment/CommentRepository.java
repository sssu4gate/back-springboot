package com.gate.planner.gate.dao.coment;

import com.gate.planner.gate.model.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
