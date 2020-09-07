package com.gate.planner.gate.service;

import com.gate.planner.gate.dao.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public String exampleService() {
        return "hello";
    }
}
