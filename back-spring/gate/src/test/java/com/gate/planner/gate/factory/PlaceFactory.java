package com.gate.planner.gate.factory;

import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.exception.place.PlaceNotExistException;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperRequestDto;
import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.model.entity.place.PlaceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceFactory {
    @Autowired
    public PlaceRepository placeRepository;

    private Long first_id = Long.parseLong("11124718");
    private String fist_place_name = "숭실대학교";
    private String first_address_name = "서울 동작구 상도동 511";
    private String first_x = "126.95781764313084";
    private String first_y = "37.495853033944364";
    private String first_category_name = "교육,학문 > 학교 > 대학교";
    private String first_category = PlaceCategory.SC4.toString();

    private Long second_id = Long.parseLong("17361144");
    private String second_place_name = "숭실대학교 정보과학관";
    private String second_address_name = "서울 동작구 상도동 509";
    private String second_x = "126.95976562412";
    private String second_y = "37.4945751847859";
    private String second_category_name = "교육,학문 > 학교부속시설";
    private String second_category = PlaceCategory.SC4.toString();


    public PlaceWrapperRequestDto returnPlaceWrapperDto() {
        return new PlaceWrapperRequestDto(first_id, 5000, "12:00~13:00");
    }

    public Place findPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(PlaceNotExistException::new);
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public List<PlaceWrapperRequestDto> returnFirstPlaceWrapperDtoList() {
        ArrayList<PlaceWrapperRequestDto> placeWrappers = new ArrayList<>();
        PlaceWrapperRequestDto placeWrapperRequestDto = new PlaceWrapperRequestDto(first_id, 5000, "1200~1300");
        placeWrappers.add(placeWrapperRequestDto);

        return placeWrappers;
    }

    public List<PlaceWrapperRequestDto> returnSecondPlaceWrapperDtoList() {
        ArrayList<PlaceWrapperRequestDto> placeWrappers = new ArrayList<>();
        PlaceWrapperRequestDto placeWrapperRequestDto = new PlaceWrapperRequestDto(second_id, 10000, "1500~1600");
        placeWrappers.add(placeWrapperRequestDto);

        return placeWrappers;
    }

    public List<PlaceDto> returnFirstPlaceDtoList() {
        ArrayList<PlaceDto> places = new ArrayList();
        PlaceDto place = new PlaceDto(first_id, first_address_name, fist_place_name, first_category_name, first_category, first_x, first_y);
        places.add(place);

        return places;
    }

    public List<PlaceDto> returnSecondPlaceDtoList() {
        ArrayList<PlaceDto> places = new ArrayList();
        PlaceDto place = new PlaceDto(second_id, second_address_name, second_place_name, second_category_name, second_category, second_x, second_y);
        places.add(place);

        return places;
    }

    public Long getFirst_id() {
        return first_id;
    }

    public Long getSecond_id() {
        return second_id;
    }
}
