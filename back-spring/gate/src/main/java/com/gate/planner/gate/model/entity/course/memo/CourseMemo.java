package com.gate.planner.gate.model.entity.course.memo;

import com.gate.planner.gate.model.entity.course.Course;
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
public class CourseMemo {
    @Id
    @GeneratedValue
    Long id;

    CourseMemoType type;

    String content;

    @ManyToOne
    Course course;

    @Builder
    public CourseMemo(CourseMemoType type, String content, Course course) {
        this.type = type;
        this.content = content;
        this.course = course;
    }

}
