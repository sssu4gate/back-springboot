package com.gate.planner.gate.model.entity.place;

import com.gate.planner.gate.model.entity.course.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PlaceWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    int cost;

    @Setter
    String time;

    @ManyToOne
    Place place;

    @ManyToOne
    Course course;

    @Builder
    public PlaceWrapper(int cost, String time, Place place, Course course) {
        this.cost = cost;
        this.time = time;
        this.place = place;
        this.course = course;
    }

}
