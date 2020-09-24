package com.gate.planner.gate.model.entity.course;

import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import com.gate.planner.gate.model.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String title;

    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    User user;

    @Setter
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    List<PlaceWrapper> places = new ArrayList<>();


    @Setter
    int commentNum = 0;

    @Setter
    int likeNum = 0;

    int totalCost = 0;

    @Builder
    public Course(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
