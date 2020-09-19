package com.gate.planner.gate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gate.planner.gate.model.dto.request.search.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class ApiService {
    HttpEntity<String> entity;

    @Value("${kakao.api.key}")
    String APIKEY;

    String URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + APIKEY);
        entity = new HttpEntity<>(headers);
    }

    private final RestTemplate restTemplate;

    public ArrayList<SearchResponseDto> RequestAPI(int page, String keyword) throws IOException {
        ArrayList<SearchResponseDto> result = new ArrayList<>();
        String url = UriComponentsBuilder.fromHttpUrl(URL).queryParam("query", keyword).queryParam("size", 5).queryParam("page", page).build().toUriString();
        ArrayList<LinkedHashMap> list = (ArrayList) restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class).getBody().get("documents");

        for (LinkedHashMap value : list)
            result.add(objectMapper.readValue(JSONObject.toJSONString(value), SearchResponseDto.class));

        return result;
    }
}
