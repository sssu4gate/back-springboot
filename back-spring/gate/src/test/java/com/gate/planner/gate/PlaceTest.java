package com.gate.planner.gate;

import com.gate.planner.gate.controller.PlaceController;
import com.gate.planner.gate.dao.place.PlaceWrapperRepository;
import com.gate.planner.gate.factory.CommonFactory;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.model.dto.place.PlaceWrapperRequestDto;
import com.gate.planner.gate.model.entity.user.User;
import com.gate.planner.gate.service.place.PlaceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
@Transactional
public class PlaceTest extends CommonFactory {

    @Autowired
    PlaceController placeController;

    @Autowired
    PlaceService placeService;

    @Autowired
    PlaceWrapperRepository placeWrapperRepository;

    @BeforeEach
    public void setUser() throws ParseException {
        User user = userFactory.returnSignUpUser1();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getId(), ""));
    }

    @Test
    public void savePlaceTest() {
        List<PlaceDto> places = placeFactory.returnFirstPlaceDtoList();
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> placeController.decidePlaces(places)),
                () -> {
                    for (PlaceDto place : places)
                        Assertions.assertDoesNotThrow(() -> placeFactory.findPlaceById(place.getId()));
                },
                () -> Assertions.assertNotEquals(0, placeFactory.findAll().size())
        );
    }

    @Test
    public void savePlaceWrapperTest() {
        PlaceWrapperRequestDto placeWrapperRequestDto = placeFactory.returnPlaceWrapperDto();
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> placeController.decidePlaces(placeFactory.returnFirstPlaceDtoList())),
                () -> Assertions.assertDoesNotThrow(() -> placeService.savePlaceWrapper(placeWrapperRequestDto, courseFactory.returnSaveCourse())),
                () -> Assertions.assertNotEquals(0, placeWrapperRepository.findAll().size())
        );
    }

    @Test
    public void placeDetailTest() {
        Assertions.assertDoesNotThrow(() -> placeController.decidePlaces(placeFactory.returnFirstPlaceDtoList()));
        Assertions.assertDoesNotThrow(() -> placeController.returnPlace(placeFactory.getFirst_id()));
    }
}
