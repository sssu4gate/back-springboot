package com.gate.planner.gate.model.entity.course;

import com.gate.planner.gate.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class CourseLike {
    @Id
    @GeneratedValue
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
