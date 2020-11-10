package com.gate.planner.gate.dao.coment;

import com.gate.planner.gate.model.entity.comment.Comment;
import com.gate.planner.gate.model.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    List<Comment> findAll();

    List<Comment> findByCourse_Id(Long id);

    @Override
    Optional<Comment> findById(Long aLong);
}
