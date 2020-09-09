package com.gate.planner.gate.model.entity.place;

import com.gate.planner.gate.model.entity.course.Course;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Place {
    @Id
    @GeneratedValue
    Long id;

    // 위도 x축
    @Column(length = 20)
    String latitude;
    //경도 y축
    @Column(length = 20)
    String longitude;

    String name;

    int likeNum = 0;
    int dislikeNum = 0;


    @Builder
    public Place(String latitude, String longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
