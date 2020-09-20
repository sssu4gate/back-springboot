package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.model.dto.request.place.PlaceDto;
import com.gate.planner.gate.model.entity.place.Coordinate;
import com.gate.planner.gate.model.entity.place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public Place savePlace(PlaceDto placeDto) {
        return placeRepository.findById(placeDto.getId())
                .orElse(placeRepository.save(Place.builder()
                        .id(placeDto.getId())
                        .category(placeDto.getCategory_name())
                        .address(placeDto.getAddress_name())
                        .coordinate(new Coordinate(placeDto.getX(), placeDto.getY()))
                        .name(placeDto.getPlace_name()).build()));
    }

    /*
       코스 저장 시에 필요한 1차적인 장소 저장
    */
    @Transactional
    public List<Place> decideCoursePlaces(List<PlaceDto> places) {
        ArrayList<Place> returnPlaceList = new ArrayList<>();

        for (PlaceDto place : places)
            returnPlaceList.add(savePlace(place));

        return returnPlaceList;
    }
}
