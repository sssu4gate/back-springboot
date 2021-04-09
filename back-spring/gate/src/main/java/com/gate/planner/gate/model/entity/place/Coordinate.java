package com.gate.planner.gate.model.entity.place;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Coordinate {
    @Column(name = "x", nullable = false, length = 20)
    private String x;

    @Column(name = "y", nullable = false, length = 20)
    private String y;

    @Builder
    public Coordinate(String x, String y) {
        this.x = x;
        this.y = y;
    }

}
