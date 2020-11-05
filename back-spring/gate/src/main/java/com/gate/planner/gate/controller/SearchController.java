package com.gate.planner.gate.controller;

import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.service.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final ApiService apiService;

    @GetMapping
    public ArrayList<PlaceDto> getSearchResult(@RequestParam String keyword, @RequestParam int page) throws IOException {
        return apiService.callLocationAPI(page, keyword);
    }

}
