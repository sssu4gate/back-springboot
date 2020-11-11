package com.gate.planner.gate.dao.place;

import com.gate.planner.gate.model.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByNameContaining(String name);
}

