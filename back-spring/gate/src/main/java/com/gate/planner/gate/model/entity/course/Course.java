package com.gate.planner.gate.model.entity.course;

import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Builder
    public Course(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
