package com.gate.planner.gate.dao.place;

import com.gate.planner.gate.model.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    boolean existsByLatitudeAndLongitude(String latitude, String longitude);

    Optional<Place> findByLatitudeAndLongitude(String latitude, String longtitude);
}

