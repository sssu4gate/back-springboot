package com.gate.planner.gate.model.entity.place;

import com.gate.planner.gate.model.entity.course.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class PlaceWrapper {
    @Id
    @GeneratedValue
    Long id;

    int cost;

    String time;

    @ManyToOne
    Place place;

    @ManyToOne
    Course course;

    @Builder
    public PlaceWrapper(int cost, String time, Place place) {
        this.cost = cost;
        this.time = time;
        this.place = place;
    }

}
