package com.gate.planner.gate.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class ApiService {
    HttpEntity<String> entity;

    @Value("${kakao.api.key}")
    String APIKEY;

    @Value("${kakao.api.profile.url}")
    private String KAKAO_PROFILE_URL;
    String URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
    ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public ArrayList<PlaceDto> RequestAPI(int page, String keyword) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + APIKEY);
        entity = new HttpEntity<>(headers);
        ArrayList<PlaceDto> result = new ArrayList<>();
        String url = UriComponentsBuilder.fromHttpUrl(URL).queryParam("query", keyword).queryParam("size", 5).queryParam("page", page).build().toUriString();
        ArrayList<LinkedHashMap> list = (ArrayList<LinkedHashMap>) restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class).getBody().get("documents");

        for (LinkedHashMap value : list)
            result.add(objectMapper.readValue(JSONObject.toJSONString(value), PlaceDto.class));

        return result;
    }

    public ResponseEntity<JSONObject> requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<>(headers);

        return restTemplate.exchange(KAKAO_PROFILE_URL, HttpMethod.GET, entity, JSONObject.class);
    }
}
