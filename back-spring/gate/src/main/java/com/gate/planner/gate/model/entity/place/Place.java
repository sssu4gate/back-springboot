package com.gate.planner.gate.model.entity.place;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Place {
    @Id
    Long id;

    String name;
    String category;
    String address;

    @Embedded
    Coordinate coordinate;

    @Builder
    public Place(Long id, String address, String name, String category, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.coordinate = coordinate;
    }
}
