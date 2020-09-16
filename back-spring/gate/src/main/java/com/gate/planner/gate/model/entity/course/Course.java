package com.gate.planner.gate.model.entity.course;

import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import com.gate.planner.gate.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String title;

    String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    User user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    List<PlaceWrapper> places = new ArrayList<>();

    @ManyToMany(mappedBy = "scrapCourse")
    List<User> users = new ArrayList<>();

    int commentNum = 0;
    int likeNum = 0;
    int totalCost = 0;

    @Builder
    public Course(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
