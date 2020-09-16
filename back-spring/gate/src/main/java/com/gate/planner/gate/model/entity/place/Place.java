package com.gate.planner.gate.model.entity.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue
    Long id;

    String category;
    String title;

    @Embedded
    Coordinate coordinate;

    int likeNum = 0;
    int dislikeNum = 0;


    @Builder
    public Place(String title, String category, Coordinate coordinate) {
        this.title = title;
        this.category = category;
        this.coordinate = coordinate;
    }
}
