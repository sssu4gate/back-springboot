package com.gate.planner.gate.model.entity.course;

import com.gate.planner.gate.model.entity.course.memo.CourseMemo;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.util.DateUtil;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @Column(nullable = false)
    String title;

    @Lob
    @Setter
    String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    User user;


    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    List<PlaceWrapper> places = new ArrayList<>();

    @Setter
    @Enumerated(EnumType.STRING)
    ShareType shareType;

    @Setter
    String imgUrl;

    @Setter
    Date dateDay;

    Date createdAt = DateUtil.toAsiaTimeZone();

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    List<CourseMemo> memos;

    @Setter
    int commentNum = 0;

    @Setter
    int likeNum = 0;

    @Setter
    int totalCost = 0;

    /**
     * 행알이의 추가 코드
     */
    @Setter
    int reportNum = 0;

    /**
     * 행알이가 추가한 코드
     * 신고 횟수 5번 이상 인지 체크
     */
    @Setter
    boolean reportFlag = false;

    @Builder
    public Course(String title, String content, String imgUrl, User user, Date dateDay, ShareType shareType) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.dateDay = dateDay;
        this.user = user;
        this.shareType = shareType;
    }
}
