package com.gate.planner.gate.dao.place;

import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceWrapperRepository extends JpaRepository<PlaceWrapper, Long> {
}
