package com.gate.planner.gate.dao.course;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseLike;
import com.gate.planner.gate.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLikeRepository extends JpaRepository<CourseLike, Long> {
    Boolean existsByCourseAndUser(Course course, User user);

    void deleteByCourseAndUser(Course course, User user);
}
