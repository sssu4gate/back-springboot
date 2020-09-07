package com.gate.planner.gate.model.entity.post;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    Long id;

    @Lob
    String content;

    @ManyToOne
    User user;

    @ManyToOne
    Place place;

    @ManyToOne
    Course course;
}
