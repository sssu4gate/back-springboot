package com.gate.planner.gate.dao.course;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseShareType;
import com.gate.planner.gate.model.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByUser(User user, Pageable pageable);

    List<Course> findAllByUser_NickNameAndShareType(String nickname, Pageable pageable, CourseShareType courseShareType);

    List<Course> findAllByTotalCostIsLessThanEqualAndShareType(int cost, Pageable pageable, CourseShareType courseShareType);

    List<Course> findDistinctByTitleContainingOrContentContainingAndShareType(String tKeyword, String cKeyword, Pageable pageable, CourseShareType shareType);

    List<Course> findAllByShareType(CourseShareType shareType, Pageable pageable);

    List<Course> findAllByShareTypeAndOrderByLikeNumDesc(CourseShareType type, Pageable pageable);
}
