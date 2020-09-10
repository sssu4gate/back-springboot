package com.gate.planner.gate.model.entity.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    String address;
    String name;

    int likeNum = 0;
    int dislikeNum = 0;


    @Builder
    public Place(String address, String name) {
        this.address = address;
        this.name = name;
    }
}
