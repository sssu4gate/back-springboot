package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.model.dto.request.place.PlaceDto;
import com.gate.planner.gate.model.dto.request.place.PlaceRequestDto;
import com.gate.planner.gate.model.entity.place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final CourseService courseService;

    public void savePlaceAndCourse(PlaceRequestDto placeRequestDto) {
        List<Place> places = new ArrayList<>();
        for (PlaceDto placeDto : placeRequestDto.getPlaces()) {
            Place place = placeRepository.findByLatitudeAndLongitude(placeDto.getLatitude(), placeDto.getLongitude())
                    .orElse(placeRepository.save(
                            Place.builder()
                                    .latitude(placeDto.getLatitude())
                                    .longitude(placeDto.getLongitude())
                                    .name(placeDto.getName()).build()));
            places.add(place);
        }
        courseService.saveCourse(placeRequestDto.getCourseName(), placeRequestDto.getUserName());
    }
}
