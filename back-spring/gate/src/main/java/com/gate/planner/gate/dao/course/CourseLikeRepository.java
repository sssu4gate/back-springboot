package com.gate.planner.gate.dao.course;

import com.gate.planner.gate.dao.course.projection.CourseOnly;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.like.CourseLike;
import com.gate.planner.gate.model.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseLikeRepository extends JpaRepository<CourseLike, Long> {
    Boolean existsByCourseAndUser(Course course, User user);

    Void deleteByCourseAndUser(Course course, User user);

    List<CourseOnly> findAllByUser(User user, Pageable pageable);

}
