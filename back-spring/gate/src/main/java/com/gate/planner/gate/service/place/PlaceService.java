package com.gate.planner.gate.service.place;

import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.dao.place.PlaceWrapperRepository;
import com.gate.planner.gate.exception.place.PlaceNotExistException;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import com.gate.planner.gate.model.entity.course.Course;
import com.gate.planner.gate.model.entity.place.Coordinate;
import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.place.PlaceWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceWrapperRepository placeWrapperRepository;

    /*
        place저장
     */
    @Transactional
    private Place savePlace(PlaceDto placeDto) {
        return placeRepository.findById(placeDto.getId())
                .orElse(placeRepository.save(Place.builder()
                        .id(placeDto.getId())
                        .category(placeDto.getCategory_name())
                        .address(placeDto.getAddress_name())
                        .coordinate(new Coordinate(placeDto.getX(), placeDto.getY()))
                        .name(placeDto.getPlace_name()).build()));
    }

    /*
       코스 저장 시에 필요한 1차적인 장소 List에 담아서 반환
    */
    @Transactional
    public List<PlaceDto> decideCoursePlaces(List<PlaceDto> places) {
        ArrayList<PlaceDto> returnPlaceList = new ArrayList<>();

        for (PlaceDto place : places)
            returnPlaceList.add(new PlaceDto(savePlace(place)));

        return returnPlaceList;
    }

    /*
        코스에 저장할 PlaceWrapper(Place + 기타 정보들)을 저장
     */
    @Transactional
    public PlaceWrapper savePlaceWrapper(PlaceWrapperDto placeWrapperDto, Course course) {
        Place place = placeRepository.findById(placeWrapperDto.getId()).orElseThrow(PlaceNotExistException::new);
        return placeWrapperRepository.save(PlaceWrapper.builder()
                .place(place)
                .course(course)
                .cost(placeWrapperDto.getCost())
                .time(placeWrapperDto.getTime())
                .build());
    }
}
