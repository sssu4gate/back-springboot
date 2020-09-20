package com.gate.planner.gate.controller;

import com.gate.planner.gate.dao.place.PlaceRepository;
import com.gate.planner.gate.model.dto.request.place.PlaceDto;
import com.gate.planner.gate.model.entity.place.Place;
import com.gate.planner.gate.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceRepository placeRepository;

    @PostMapping("/save")
    public List<Place> decidePlaces(@RequestBody List<PlaceDto> places) {
        return placeService.decideCoursePlaces(places);
    }

    @GetMapping("/{id}")
    public Place returnPlace(@PathVariable Long id) {
        return placeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
