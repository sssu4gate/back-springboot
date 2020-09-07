package com.gate.planner.gate.model.entity.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue
    Long id;

    String imgUrl;

    @ManyToOne
    Post post;
}
