package com.gate.planner.gate.controller;

import com.gate.planner.gate.service.ApiService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final ApiService apiService;

    @GetMapping
    public JSONObject getSearchResult(@RequestParam String keyword, @RequestParam int page) throws UnsupportedEncodingException {
        return apiService.RequestAPI(page, keyword);
    }

}
