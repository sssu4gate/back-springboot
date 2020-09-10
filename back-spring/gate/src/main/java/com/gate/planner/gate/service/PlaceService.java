package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.model.dto.request.place.PlacePostDto;
import com.gate.planner.gate.model.entity.place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> savePlaceAndCourse(PlacePostDto[] placePostsDto) {
        List<Place> places = new ArrayList<>();
        for (PlacePostDto placePostDto : placePostsDto) {
            Place place = placeRepository.findByAddressAndName(placePostDto.getAddress(), placePostDto.getPlaceName())
                    .orElse(placeRepository.save(
                            Place.builder()
                                    .address(placePostDto.getAddress())
                                    .name(placePostDto.getPlaceName()).build()));
            places.add(place);
        }
        return places;
    }
}
