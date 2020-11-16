package com.gate.planner.gate.model.entity.course;

import com.gate.planner.gate.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @Builder
    public CourseReport(Course course, User user){
        this.course = course;
        this.user = user;
    }
}
