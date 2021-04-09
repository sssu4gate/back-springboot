package com.gate.planner.gate.dao.course;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.ShareType;
import com.gate.planner.gate.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByUser(User user, Pageable pageable);

    List<Course> findAllByUser_NickNameAndShareTypeAndReportFlagIsFalse(String nickname, Pageable pageable, ShareType shareType);

    List<Course> findAllByTotalCostIsLessThanEqualAndShareTypeAndReportFlagIsFalse(int cost, Pageable pageable, ShareType shareType);

    List<Course> findDistinctByTitleContainingOrContentContainingAndShareTypeAndReportFlagIsFalse(String tKeyword, String cKeyword, Pageable pageable, ShareType shareType);

    List<Course> findAllByShareTypeAndReportFlagIsFalse(ShareType shareType, Pageable pageable);

    Page<Course> findAllByShareTypeAndReportFlagIsFalseOrderByLikeNumDesc(ShareType type, Pageable pageable);

    List<Course> findAllByDateDayBetweenAndUser(Date startDate, Date endDate, User user, Pageable pageable);
}
