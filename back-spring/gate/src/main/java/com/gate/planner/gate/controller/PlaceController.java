package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.request.place.PlaceRequestDto;
import com.gate.planner.gate.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/save")
    public void savePlace(@RequestBody PlaceRequestDto placeRequestDto) {
        placeService.savePlaceAndCourse(placeRequestDto);
    }
}
