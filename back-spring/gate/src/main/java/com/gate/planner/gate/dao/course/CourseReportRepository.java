package com.gate.planner.gate.dao.course;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.CourseReport;
import com.gate.planner.gate.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 행알이가 추가한 코드
 */
public interface CourseReportRepository extends JpaRepository<CourseReport, Long> {
    boolean existsByCourseAndUser(Course course, User user);
}
