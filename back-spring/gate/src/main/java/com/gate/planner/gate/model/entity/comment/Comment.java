package com.gate.planner.gate.model.entity.comment;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    String content;

    Date createdAt = DateUtil.toAsiaTimeZone();

    @ManyToOne
    User user;

    @ManyToOne
    Course course;

    @Builder
    public Comment(String content, User user, Course course) {
        this.content = content;
        this.user = user;
        this.course = course;
    }
}
