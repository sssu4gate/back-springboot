package com.gate.planner.gate.model.entity.course.like;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CourseLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User user;

    @ManyToOne
    Course course;


    @Builder
    public CourseLike(Course course, User user) {
        this.course = course;
        this.user = user;
    }
}
