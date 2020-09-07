package com.gate.planner.gate.model.entity.course;

import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    Long id;

    String name;

    @ManyToOne
    User user;

    int commentNum = 0;
    int likeNum = 0;
    int dislikeNum = 0;
}
