package com.gate.planner.gate.dao.post;

import com.gate.planner.gate.model.entity.post.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
