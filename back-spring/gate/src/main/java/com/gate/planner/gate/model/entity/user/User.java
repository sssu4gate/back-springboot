package com.gate.planner.gate.model.entity.user;

import com.gate.planner.gate.model.entity.course.Course;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    Long id;

    @Column(unique = true, length = 10)
    String userName;

    @Column(length = 20)
    String password;

    @Column(unique = true, length = 40)
    String email;

    @Column(unique = true, length = 20)
    String nickName;

    @Builder
    public User(String userName, String password, String Email, String nickName) {
        this.userName = userName;
        this.password = password;
        this.email = Email;
        this.nickName = nickName;
    }
}
