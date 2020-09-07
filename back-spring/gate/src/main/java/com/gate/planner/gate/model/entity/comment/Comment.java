package com.gate.planner.gate.model.entity.comment;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    Long id;

    String content;

    @ManyToOne
    User user;

    @ManyToOne
    Course course;
}
