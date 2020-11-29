package com.gate.planner.gate.model.entity.course.report;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 행알이가 추가한 코드
 */
@Getter
@NoArgsConstructor
@Entity
public class CourseReport {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Course course;

    @Enumerated(EnumType.STRING)
    CourseReportType reportType;

    @Builder
    public CourseReport(Course course, User user, CourseReportType type) {
        this.course = course;
        this.reportType = type;
        this.user = user;
    }
}
