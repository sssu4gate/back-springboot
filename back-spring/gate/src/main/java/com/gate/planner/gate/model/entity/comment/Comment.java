package com.gate.planner.gate.model.entity.comment;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String content;

    Date createdAt = DateUtil.toAsiaTimeZone();

    @ManyToOne
    User user;

    @ManyToOne
    Course course;

    @ManyToOne
    Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    List<Comment> childComment;

    @Builder
    public Comment(String content, User user, Course course) {
        this.content = content;
        this.user = user;
        this.course = course;
    }
}
