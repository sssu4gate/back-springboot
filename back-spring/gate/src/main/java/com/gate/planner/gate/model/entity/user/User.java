package com.gate.planner.gate.model.entity.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    Long id;

    @Column(unique = true, length = 20)
    String nickName;

    String accessToken;

    String refreshToken;

    @Enumerated(EnumType.STRING)
    Gender gender;


    @Builder
    public User(Long id, String refreshToken, Gender gender, String accessToken, String nickName) {
        this.id = id;
        this.gender = gender;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.nickName = nickName;
    }

    public void changeToken(String refreshToken, String accessToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
