package com.gate.planner.gate.model.entity.post;

import lombok.Builder;
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

    @Builder
    public Image(Post post, String imgUrl) {
        this.imgUrl = imgUrl;
        this.post = post;
    }
}
