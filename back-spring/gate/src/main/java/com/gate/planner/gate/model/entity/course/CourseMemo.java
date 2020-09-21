package com.gate.planner.gate.model.entity.course;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
public class CourseMemo {
    @Id
    @GeneratedValue
    Long id;

    String content;

    @ManyToOne
    Course course;

    @Builder
    public CourseMemo(String content, Course course) {
        this.content = content;
        this.course = course;
    }
}
