package com.gate.planner.gate.factory;

import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.exception.place.PlaceNotExistException;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperDto;
import com.gate.planner.gate.model.entity.place.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceFactory {
    @Autowired
    public PlaceRepository placeRepository;

    private Long id = Long.parseLong("11124718");
    private String place_name = "숭실대학교";
    private String address_name = "서울 동작구 상도동 511";
    private String x = "126.95781764313084";
    private String y = "37.495853033944364";
    private String category_name = "교육,학문 > 학교 > 대학교";


    public PlaceWrapperDto returnPlaceWrapperDto() {
        return new PlaceWrapperDto(id, 5000, "12:00~13:00");
    }

    public Place findPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(PlaceNotExistException::new);
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public List<PlaceWrapperDto> returnPlaceWrapperDtoList() {
        ArrayList<PlaceWrapperDto> placeWrappers = new ArrayList<>();
        PlaceWrapperDto placeWrapperDto = new PlaceWrapperDto(id, 5000, "1200~1300");
        placeWrappers.add(placeWrapperDto);

        return placeWrappers;
    }

    public List<PlaceDto> returnPlaceDtoList() {
        ArrayList<PlaceDto> places = new ArrayList();
        PlaceDto place = new PlaceDto(id, address_name, place_name, category_name, x, y);
        places.add(place);

        return places;
    }

    public Long getId() {
        return id;
    }
}
