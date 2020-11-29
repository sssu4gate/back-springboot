package com.gate.planner.gate.dao.course;

import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.course.ShareType;
import com.gate.planner.gate.model.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByUser(User user, Pageable pageable);

    List<Course> findAllByUser_NickNameAndShareType(String nickname, Pageable pageable, ShareType shareType);

    List<Course> findAllByTotalCostIsLessThanEqualAndShareType(int cost, Pageable pageable, ShareType shareType);

    List<Course> findDistinctByTitleContainingOrContentContainingAndShareType(String tKeyword, String cKeyword, Pageable pageable, ShareType shareType);

    List<Course> findAllByShareType(ShareType shareType, Pageable pageable);

    List<Course> findAllByShareTypeOrderByLikeNumDesc(ShareType type, Pageable pageable);
}
