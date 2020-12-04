package com.gate.planner.gate.model.entity.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Place {
    @Id
    Long id;

    String name;
    String categoryName;
    String address;
    @Enumerated(EnumType.STRING)
    PlaceCategory groupCategory;

    @Embedded
    Coordinate coordinate;

    @Builder
    public Place(Long id, String address, String name, String categoryName, PlaceCategory category, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.groupCategory = category;
        this.categoryName = categoryName;
        this.coordinate = coordinate;
    }
}
