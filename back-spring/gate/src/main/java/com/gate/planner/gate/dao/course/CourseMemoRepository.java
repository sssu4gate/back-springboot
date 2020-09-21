package com.gate.planner.gate.dao.course;

import com.gate.planner.gate.model.entity.course.CourseMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMemoRepository extends JpaRepository<CourseMemo, Long> {
}
