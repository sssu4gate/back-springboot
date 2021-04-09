package com.gate.planner.gate.model.entity.course.memo;

import com.gate.planner.gate.model.entity.course.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CourseMemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
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
